package by.clevertec.microsearch.repository;

import static org.junit.jupiter.api.Assertions.*;

import by.clevertec.microsearch.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
@Sql(value = "classpath:sql-test/init-test-repository.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class CommentRepositoryTest {

    private final CommentRepository commentRepository;

    @Test
    public void testSearchReturnFalse_whenWordExistInDb() {
        // given
        String queryString = "комментарий";
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Comment> results = commentRepository.search(queryString, pageable);

        // then
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchReturnTrue_whenWordNonExistDb() {
        // given
        String queryString = "несуществующееслово";
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Comment> results = commentRepository.search(queryString, pageable);

        // then
        assertTrue(results.isEmpty());
    }
}
