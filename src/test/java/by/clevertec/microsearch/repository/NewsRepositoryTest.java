package by.clevertec.microsearch.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.clevertec.microsearch.domain.News;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
@Sql(value = "classpath:sql-test/init-test-repository.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class NewsRepositoryTest {

    private final NewsRepository newsRepository;

    @Test
    public void testSearchReturnFalse_whenWordExistInDb() {
        // given
        String queryString = "новость";
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<News> results = newsRepository.search(queryString, pageable);

        // then
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchReturnTrue_whenWordNonExistDb() {
        // given
        String queryString = "несуществующееслово";
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<News> results = newsRepository.search(queryString, pageable);

        // then
        assertTrue(results.isEmpty());
    }
}
