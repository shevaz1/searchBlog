package com.shevaz.searchblog.blog;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class NaverApiResponse {
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;

    private List<NaverBlogDto> items;
}
