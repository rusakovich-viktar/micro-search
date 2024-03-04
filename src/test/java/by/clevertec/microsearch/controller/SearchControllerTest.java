package by.clevertec.microsearch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import by.clevertec.microsearch.dto.CommentResponseDto;
import by.clevertec.microsearch.dto.NewsResponseDto;
import by.clevertec.microsearch.service.SearchService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private SearchService searchService;

    @Test
    public void testSearchNews() {
        String text = "test";
        Pageable pageable = PageRequest.of(0, 10);
        Page<NewsResponseDto> page = Page.empty();

        Mockito.when(searchService.searchNews(text, pageable)).thenReturn(page);

        ResponseEntity<Page<NewsResponseDto>> response = searchController.searchNews(text, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }

    @Test
    public void testSearchComments() {
        String text = "test";
        Pageable pageable = PageRequest.of(0, 10);
        Page<CommentResponseDto> page = Page.empty();

        Mockito.when(searchService.searchComments(text, pageable)).thenReturn(page);

        ResponseEntity<Page<CommentResponseDto>> response = searchController.searchComments(text, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }
}
