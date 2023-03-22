package com.shevaz.searchblog.blog;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NaverBlogDto {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;
}
