package com.shevaz.searchblog;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
public class RestTemplateTest {

    @Test
    @DisplayName("GET Test")
    void getTest() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "축구");
        params.add("page", String.valueOf(5));
        params.add("size", String.valueOf(5));

        URI uri=UriComponentsBuilder
                .fromUriString("https://dapi.kakao.com/v2/search/blog")
                .queryParams(params)
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK fe6d33f16dddc557ba78d0604946d058");
        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        log.info("result status code : {}", result.getStatusCode());
        log.info("result headers : {}",result.getHeaders());
        log.info("result body : {}",result.getBody());

    }
}
