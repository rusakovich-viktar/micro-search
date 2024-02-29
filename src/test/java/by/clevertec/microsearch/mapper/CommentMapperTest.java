package by.clevertec.microsearch.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import by.clevertec.microsearch.domain.Comment;
import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.dto.CommentResponseDto;
import by.clevertec.microsearch.util.DataTestBuilder;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CommentMapperTest {

    private final CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @Test
    void testToDtoShouldReturnResponseDto() {
        News news = DataTestBuilder.builder()
                .build()
                .buildNews();

        Comment comment = DataTestBuilder.builder()
                .build().buildComment(news);

        CommentResponseDto expected = DataTestBuilder.builder()
                .build()
                .buildCommentResponseDto();

        CommentResponseDto actual = commentMapper.toDto(comment);

        assertThat(actual)
                .hasFieldOrPropertyWithValue(Comment.Fields.id, expected.getId())
                .hasFieldOrPropertyWithValue(Comment.Fields.time, expected.getTime())
                .hasFieldOrPropertyWithValue(Comment.Fields.updateTime, expected.getUpdateTime())
                .hasFieldOrPropertyWithValue(Comment.Fields.username, expected.getUsername())
                .hasFieldOrPropertyWithValue(Comment.Fields.text, expected.getText());
    }
}