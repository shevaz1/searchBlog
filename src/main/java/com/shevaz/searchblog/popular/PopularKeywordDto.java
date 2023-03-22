package com.shevaz.searchblog.popular;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PopularKeywordDto {
    private String keyword;
    private Integer keywordCount;
}
