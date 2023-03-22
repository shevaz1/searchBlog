package com.shevaz.searchblog.blog;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MetaDto {
    private Boolean is_end;
    private Integer pageable_count;
    private Integer total_count;
}
