package com.shevaz.searchblog.blog;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class KaKaoApiResponse {
    private MetaDto meta;
    private List<KaKaoBlogDto> documents;
}
