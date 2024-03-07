package by.clevertec.microsearch.service.impl;

import by.clevertec.microsearch.dto.CommentResponseDto;
import by.clevertec.microsearch.dto.NewsResponseDto;
import by.clevertec.microsearch.mapper.CommentMapper;
import by.clevertec.microsearch.mapper.NewsMapper;
import by.clevertec.microsearch.repository.CommentRepository;
import by.clevertec.microsearch.repository.NewsRepository;
import by.clevertec.microsearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для поиска новостей и комментариев.
 */
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;
    private final NewsMapper newsMapper;
    private final CommentMapper commentMapper;

    /**
     * Поиск новостей по тексту.
     *
     * @param queryString строка запроса
     * @param pageable    параметры пагинации
     * @return страница с результатами поиска новостей
     */
    public Page<NewsResponseDto> searchNews(String queryString, Pageable pageable) {
        return newsRepository.search(queryString, pageable)
                .map(newsMapper::toDto);
    }

    /**
     * Поиск комментариев по тексту.
     *
     * @param queryString строка запроса
     * @param pageable    параметры пагинации
     * @return страница с результатами поиска комментариев
     */
    public Page<CommentResponseDto> searchComments(String queryString, Pageable pageable) {
        return commentRepository.search(queryString, pageable)
                .map(commentMapper::toDto);
    }

}
