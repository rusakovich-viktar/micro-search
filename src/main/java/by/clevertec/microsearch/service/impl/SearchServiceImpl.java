package by.clevertec.microsearch.service.impl;

import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.repository.NewsRepository;
import by.clevertec.microsearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
@EnableCaching
public class SearchServiceImpl implements SearchService {

    private final NewsRepository newsRepository;
//    private final NewsMapper newsMapper;

    public Page<News> search(String queryString, Pageable pageable) {
        return newsRepository.search(queryString, pageable);
//                .map(newsMapper::toDto);
    }

}
