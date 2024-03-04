package by.clevertec.microsearch.mapper;

import by.clevertec.microsearch.domain.Comment;
import by.clevertec.microsearch.dto.CommentResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

    @Mapping(source = "news.id", target = "newsId")
    CommentResponseDto toDto(Comment entity);

}
