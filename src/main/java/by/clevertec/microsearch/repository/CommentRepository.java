package by.clevertec.microsearch.repository;

import by.clevertec.microsearch.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий для работы с сущностями "Комментарий".
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Поиск комментариев по тексту или имени пользователя.
     *
     * @param queryString строка запроса
     * @param pageable    параметры пагинации
     * @return страница с результатами поиска комментариев
     */
    @Query(value = "SELECT c FROM Comment c WHERE "
            + "(:queryString is null or lower(c.text) like lower(concat('%', :queryString, '%'))) or "
            + "(:queryString is null or lower(c.username) like lower(concat('%', :queryString, '%')))")
    Page<Comment> search(@Param("queryString") String queryString, Pageable pageable);

}
