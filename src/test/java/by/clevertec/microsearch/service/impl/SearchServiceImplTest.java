package by.clevertec.microsearch.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.clevertec.microsearch.domain.Comment;
import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.dto.CommentResponseDto;
import by.clevertec.microsearch.dto.NewsResponseDto;
import by.clevertec.microsearch.mapper.CommentMapper;
import by.clevertec.microsearch.mapper.NewsMapper;
import by.clevertec.microsearch.repository.CommentRepository;
import by.clevertec.microsearch.repository.NewsRepository;
import by.clevertec.microsearch.util.DataTestBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class SearchServiceImplTest {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private NewsMapper newsMapper;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private SearchServiceImpl searchService;



    @Test
    public void testSearchNews() {
        // given
        String queryString = "новость";
        Pageable pageable = PageRequest.of(0, 10);
        News news = DataTestBuilder.builder().build().buildNews();
        NewsResponseDto expectedNewsResponseDto = DataTestBuilder.builder().build().buildNewsResponseDto();

        when(newsRepository.search(queryString, pageable)).thenReturn(new PageImpl<>(List.of(news)));
        when(newsMapper.toDto(news)).thenReturn(expectedNewsResponseDto);

        // when
        Page<NewsResponseDto> results = searchService.searchNews(queryString, pageable);

        // then
        assertFalse(results.isEmpty());
        assertEquals(expectedNewsResponseDto, results.getContent().get(0));
    }

    @Test
    public void testSearchComments() {
        // given
        String queryString = "комментарий";
        Pageable pageable = PageRequest.of(0, 10);
        News news = DataTestBuilder.builder().build().buildNews();

        Comment comment = DataTestBuilder.builder().build().buildComment(news);
        CommentResponseDto expectedCommentResponseDto = DataTestBuilder.builder().build().buildCommentResponseDto();

        when(commentRepository.search(queryString, pageable)).thenReturn(new PageImpl<>(List.of(comment)));
        when(commentMapper.toDto(comment)).thenReturn(expectedCommentResponseDto);

        // when
        Page<CommentResponseDto> results = searchService.searchComments(queryString, pageable);

        // then
        assertFalse(results.isEmpty());
        assertEquals(expectedCommentResponseDto, results.getContent().get(0));
    }
}

