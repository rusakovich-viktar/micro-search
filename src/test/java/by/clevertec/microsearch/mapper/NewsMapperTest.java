package by.clevertec.microsearch.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.domain.News.Fields;
import by.clevertec.microsearch.dto.NewsResponseDto;
import by.clevertec.microsearch.util.DataTestBuilder;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class NewsMapperTest {

    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    @Test
    void testToDtoShouldReturnResponseDto() {
        News news = DataTestBuilder.builder()
                .build()
                .buildNews();

        NewsResponseDto expected = DataTestBuilder.builder()
                .build()
                .buildNewsResponseDto();

        NewsResponseDto actual = newsMapper.toDto(news);

        assertThat(actual)
                .hasFieldOrPropertyWithValue(News.Fields.id, expected.getId())
                .hasFieldOrPropertyWithValue(News.Fields.time, expected.getTime())
                .hasFieldOrPropertyWithValue(News.Fields.updateTime, expected.getUpdateTime())
                .hasFieldOrPropertyWithValue(Fields.title, expected.getTitle())
                .hasFieldOrPropertyWithValue(News.Fields.text, expected.getText());
    }
}
