package com.shevaz.searchblog.popular;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "search_keyword")
@Getter
@NoArgsConstructor
public class SearchKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "created_dt", nullable = false)
    private LocalDateTime createdDt;

    public SearchKeyword(String keyword) {
        this.keyword = keyword;
        this.createdDt = LocalDateTime.now();
    }
}
