package com.shevaz.searchblog.popular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Long> {

    @Query(value="select keyword as keyword, count(keyword) as keywordCount " +
            "from search_keyword group by keyword order by keywordCount desc limit 10", nativeQuery = true)
    List<PopularKeywordInterface> findGroupByKeywordAndCount();

}
