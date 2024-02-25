package by.clevertec.microsearch.mapper;

import by.clevertec.microsearch.domain.News;
import by.clevertec.microsearch.dto.NewsResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NewsMapper {

    NewsResponseDto toDto(News entity);

}
