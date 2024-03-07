package by.clevertec.microsearch.repository;

import by.clevertec.microsearch.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий для работы с сущностями "Новости".
 */
public interface NewsRepository extends JpaRepository<News, Long> {

    /**
     * Поиск новостей по заголовку или тексту.
     *
     * @param queryString строка запроса
     * @param pageable    параметры пагинации
     * @return страница с результатами поиска новостей
     */
    @Query(value = "SELECT n FROM News n WHERE "
            + "(:queryString is null or lower(n.title) like lower(concat('%', :queryString, '%'))) or "
            + "(:queryString is null or lower(n.text) like lower(concat('%', :queryString, '%')))")
    Page<News> search(@Param("queryString") String queryString, Pageable pageable);

}
