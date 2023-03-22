package com.shevaz.searchblog.popular;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/popular")
@RequiredArgsConstructor
public class PopularController {
    private final PopularService popularService;

    @GetMapping(value = "/keyword")
    public ResponseEntity getPopularKeywordList() {
        List<PopularKeywordInterface> result = popularService.getPopularKeyword();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
