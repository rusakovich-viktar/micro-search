package by.clevertec.microsearch.mapper;

import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.dto.NewsResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Маппер для преобразования сущности "Новость" в DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NewsMapper {

    /**
     * Преобразует сущность "Новость" в DTO.
     *
     * @param entity сущность "Новость"
     * @return DTO новости
     */
    NewsResponseDto toDto(News entity);

}
