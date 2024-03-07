package by.clevertec.microsearch.mapper;

import by.clevertec.microsearch.domain.Comment;
import by.clevertec.microsearch.dto.CommentResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Маппер для преобразования сущности "Комментарий" в DTO и обратно.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

    /**
     * Преобразует сущность "Комментарий" в DTO.
     *
     * @param entity сущность "Комментарий"
     * @return DTO комментария
     */
    @Mapping(source = "news.id", target = "newsId")
    CommentResponseDto toDto(Comment entity);

}
