package com.shevaz.searchblog.popular;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PopularService {

    private final SearchKeywordRepository searchKeywordRepository;

    @Async
    @Transactional
    public void saveSearchKeyword(String keyword) throws DataAccessException {
        searchKeywordRepository.save(new SearchKeyword(keyword));
    }

    public List<PopularKeywordInterface> getPopularKeyword() {
        return searchKeywordRepository.findGroupByKeywordAndCount();
    }
}
