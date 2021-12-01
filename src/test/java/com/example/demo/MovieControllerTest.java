package com.example.demo;

import com.example.demo.data.Movie;
import com.example.demo.data.MovieRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MovieRepository movieRepository;

    @Test
    public void shouldReturnMovie() throws Exception {
        long id = 1;
        BDDMockito.given(movieRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Movie(id, "test", LocalDate.of(2020, 2, 10))));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movie/" + id))
                .andExpect(MockMvcResultMatchers.jsonPath("$['name']", Matchers.is("test")));
    }

    @Test
    public void shouldCreateMovie() throws Exception {
        Movie movie = new Movie(1L, "test", LocalDate.of(2010, 1, 1));
        BDDMockito.given(movieRepository.save(Mockito.any())).willReturn(movie);
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/movie")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\": \"test\", \"releaseDate\": \"2010-01-01\"}")
                ).andExpect(MockMvcResultMatchers.jsonPath("$['name']", Matchers.is(movie.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$['releaseDate']", Matchers.is(movie.getReleaseDate().toString())));
    }
}
