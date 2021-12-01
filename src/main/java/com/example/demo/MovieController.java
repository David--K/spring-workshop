package com.example.demo;

import com.example.demo.data.Movie;
import com.example.demo.data.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("movie")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("{id}")
    public Optional<Movie> findById(@PathVariable Long id) {
        return movieRepository.findById(id);
    }
}
