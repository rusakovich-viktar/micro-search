package by.clevertec.microsearch.service;


import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.dto.NewsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchService {


    Page<News> search(String queryString, Pageable pageable);


}
