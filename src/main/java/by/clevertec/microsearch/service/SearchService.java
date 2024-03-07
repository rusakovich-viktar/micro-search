package by.clevertec.microsearch.service;

import by.clevertec.microsearch.dto.CommentResponseDto;
import by.clevertec.microsearch.dto.NewsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    Page<NewsResponseDto> searchNews(String queryString, Pageable pageable);

    Page<CommentResponseDto> searchComments(String queryString, Pageable pageable);

}
