package by.clevertec.microsearch.controller;

import by.clevertec.microsearch.dto.CommentResponseDto;
import by.clevertec.microsearch.dto.NewsResponseDto;
import by.clevertec.microsearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для поиска новостей и комментариев.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    /**
     * Поиск новостей по тексту.
     *
     * @param text     текст для поиска
     * @param pageable параметры пагинации
     * @return страница с результатами поиска новостей
     */
    @GetMapping("/news")
    public ResponseEntity<Page<NewsResponseDto>> searchNews(@RequestParam String text, Pageable pageable) {
        Page<NewsResponseDto> results = searchService.searchNews(text, pageable);
        return ResponseEntity.ok(results);
    }

    /**
     * Поиск комментариев по тексту.
     *
     * @param text     текст для поиска
     * @param pageable параметры пагинации
     * @return страница с результатами поиска комментариев
     */
    @GetMapping("/comments")
    public ResponseEntity<Page<CommentResponseDto>> searchComments(@RequestParam String text, Pageable pageable) {
        Page<CommentResponseDto> results = searchService.searchComments(text, pageable);
        return ResponseEntity.ok(results);
    }

}
