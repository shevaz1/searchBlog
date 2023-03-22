package com.shevaz.searchblog.blog;

import com.shevaz.searchblog.popular.PopularService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Slf4j
public class BlogRestController {

    private final BlogService blogService;
    private final PopularService popularService;

    @GetMapping(value = "/list")
    public ResponseEntity getBlogList(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                                   @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                   @RequestParam(value = "sort", defaultValue = "accuracy", required = false) String sort, HttpServletRequest httpServletRequest) {
        log.info("request, uri[{}], keyword[{}], sort[{}], size[{}], page[{}]", httpServletRequest.getRequestURI(), keyword, sort, size, page);
        try {
            popularService.saveSearchKeyword(keyword);
        } catch (DataAccessException de) {
            log.error("Keyword Save Failed.");
        }
        try {
            BlogResponse response = blogService.getBlogList(keyword, sort, size, page);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Open API Server Error");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
