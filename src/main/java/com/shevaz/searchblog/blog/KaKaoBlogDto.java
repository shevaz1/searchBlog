package com.shevaz.searchblog.blog;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KaKaoBlogDto {
    private String blogname;
    private String contents;
    private String datetime;
    private String thumbnail;
    private String title;
    private String url;
}
