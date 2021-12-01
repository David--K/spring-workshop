package com.example.demo.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

@DataJpaTest
public class MovieRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldCreateAndfindMovieByName() throws Exception {
        LocalDate date = LocalDate.of(2010, 1, 1);
        entityManager.persist(new Movie("test", LocalDate.of(2010, 1, 1)));
        Movie movie = movieRepository.findByName("test").get(0);
        Assertions.assertThat(movie.getName()).isEqualTo("test");
        Assertions.assertThat(movie.getReleaseDate()).isEqualTo(date);
    }
}
