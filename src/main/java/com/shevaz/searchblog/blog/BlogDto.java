package com.shevaz.searchblog.blog;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogDto {
    private String title;
    private String blogName;
    private String description;
    private String url;
    private String date;

    public BlogDto(KaKaoBlogDto kakao) {
        this.title = kakao.getTitle();
        this.blogName = kakao.getBlogname();
        this.description = kakao.getContents();
        this.url = kakao.getUrl();
        this.date = kakao.getDatetime();
    }

    public BlogDto(NaverBlogDto naver) {
        this.title = naver.getTitle();
        this.blogName = naver.getBloggername();
        this.description = naver.getDescription();
        this.url = naver.getLink();
        this.date = naver.getPostdate();
    }
}
