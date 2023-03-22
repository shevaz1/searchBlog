package com.shevaz.searchblog.blog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogService {

    private final Environment environment;

    public BlogResponse getBlogList(String keyword, String sort, Integer size, Integer page) throws Exception {

        try {
            URI kakaoUri = createURI("kakao", keyword, sort, page, size);
            URI naverUri = createURI("naver", keyword, sort, page, size);

            HttpEntity<String> kakaoEntity = createEntity("kakao");
            HttpEntity<String> naverEntity = createEntity("naver");

            RestTemplate restTemplate = new RestTemplate();

            BlogResponse response = new BlogResponse();
            List<BlogDto> blogDtoList = new ArrayList<>();

            try{
                ResponseEntity<KaKaoApiResponse> result = restTemplate.exchange(kakaoUri, HttpMethod.GET, kakaoEntity, KaKaoApiResponse.class);

                if(result.getStatusCode().is2xxSuccessful()) {
                    result.getBody().getDocuments().forEach(i -> {
                        blogDtoList.add(new BlogDto(i));
                    });
                    response.setBlogList(blogDtoList);
                    response.setDisplay(size);
                    response.setStart(page);
                    response.setTotal(result.getBody().getMeta().getTotal_count());
                }

            } catch (HttpClientErrorException e) {
                 try{
                     ResponseEntity<NaverApiResponse> naverResult = restTemplate.exchange(naverUri, HttpMethod.GET, naverEntity, NaverApiResponse.class);

                     if(naverResult.getStatusCode().is2xxSuccessful()) {
                         naverResult.getBody().getItems().forEach(i -> {
                             blogDtoList.add(new BlogDto(i));
                         });
                         response.setBlogList(blogDtoList);
                         response.setDisplay(naverResult.getBody().getDisplay());
                         response.setStart(naverResult.getBody().getStart());
                         response.setTotal(naverResult.getBody().getTotal());
                     }
                 } catch (HttpClientErrorException e2) {
                     throw e2;
                 }
            }
            return response;
        } catch (Exception e3) {
            throw e3;
        }
    }

    private HttpEntity<String> createEntity(String type) {
        if(type.equals("kakao")) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", environment.getProperty("restApi.kakao.key"));
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            return entity;
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", environment.getProperty("restApi.naver.id"));
            headers.set("X-Naver-Client-Secret", environment.getProperty("restApi.naver.secret"));
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            return entity;
        }
    }

    private URI createURI(String type, String keyword, String sort, Integer page, Integer size) {
        if(type.equals("kakao")) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("query", keyword);
            params.add("sort", sort);
            params.add("page", String.valueOf(page));
            params.add("size", String.valueOf(size));

            URI uri= UriComponentsBuilder
                    .fromUriString(environment.getProperty("restApi.kakao.url.blogSearch"))
                    .queryParams(params)
                    .encode()
                    .build()
                    .toUri();

            return uri;
        } else {
            MultiValueMap<String, String> naverParams = new LinkedMultiValueMap<>();
            naverParams.add("query", keyword);
            if(sort.equals("accuracy")) {
                naverParams.add("sort", "sim");
            } else if(sort.equals("recency")) {
                naverParams.add("sort", "date");
            }
            naverParams.add("start", String.valueOf(page));
            naverParams.add("display", String.valueOf(size));

            URI uri= UriComponentsBuilder
                    .fromUriString(environment.getProperty("restApi.naver.url.blogSearch"))
                    .queryParams(naverParams)
                    .encode()
                    .build()
                    .toUri();

            return uri;
        }

    }
}
