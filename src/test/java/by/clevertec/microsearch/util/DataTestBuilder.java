package by.clevertec.microsearch.util;

import static by.clevertec.microsearch.util.TestConstant.LOCAL_DATE_TIME;
import static by.clevertec.microsearch.util.TestConstant.NEWS_TEXT;
import static by.clevertec.microsearch.util.TestConstant.NEWS_TITLE;
import static by.clevertec.microsearch.util.TestConstant.USERNAME;

import by.clevertec.microsearch.domain.Comment;
import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.dto.CommentResponseDto;
import by.clevertec.microsearch.dto.NewsResponseDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class DataTestBuilder {

    @Builder.Default
    private Long id = TestConstant.ID_ONE;

    @Builder.Default
    private LocalDateTime time = LOCAL_DATE_TIME;

    @Builder.Default
    private LocalDateTime updateTime = LOCAL_DATE_TIME;

    @Builder.Default
    private String title = NEWS_TITLE;

    @Builder.Default
    private String text = NEWS_TEXT;

    @Builder.Default
    private String username = USERNAME;


    public News buildNews() {
        News news = new News();

        news.setId(id);
        news.setText(text);
        news.setTitle(title);
        news.setTime(time);
        news.setUpdateTime(updateTime);

        return news;
    }

    public Comment buildComment(News news) {
        Comment comment = new Comment();

        comment.setId(id);
        comment.setTime(time);
        comment.setUpdateTime(updateTime);
        comment.setText(text);
        comment.setUsername(username);
        comment.setNews(news);

        return comment;
    }


    public CommentResponseDto buildCommentResponseDto() {
        CommentResponseDto buildCommentResponseDto = new CommentResponseDto();

        buildCommentResponseDto.setId(id);
        buildCommentResponseDto.setTime(time);
        buildCommentResponseDto.setUpdateTime(time);
        buildCommentResponseDto.setText(text);
        buildCommentResponseDto.setUsername(username);
        buildCommentResponseDto.setNewsId(id);

        return buildCommentResponseDto;
    }


    public NewsResponseDto buildNewsResponseDto() {
        NewsResponseDto buildNewsResponseDto = new NewsResponseDto();

        buildNewsResponseDto.setId(id);
        buildNewsResponseDto.setTime(time);
        buildNewsResponseDto.setUpdateTime(time);
        buildNewsResponseDto.setTitle(title);
        buildNewsResponseDto.setText(text);

        return buildNewsResponseDto;
    }


}
