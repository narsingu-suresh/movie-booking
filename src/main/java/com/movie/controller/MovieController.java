package com.movie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.model.Movie;
import com.movie.projection.MovieResponse;
import com.movie.request.NewMovieRequest;
import com.movie.service.impl.MovieServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * This acts as the controller for the Movie related operations
 */
@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

	private final MovieServiceImpl movieService;

	public MovieController(MovieServiceImpl movieService) {
		this.movieService = movieService;
	}

	@GetMapping
	public List<MovieResponse> getMovies(@RequestParam(required = false) String title,
		@RequestParam(required = false) String location, @RequestParam(required = false) String genre) {
		return movieService.getAllMovies(title, location, genre);
	}

	@GetMapping("/{id}")
	public Movie getMovieById(@PathVariable("id") Integer id) {
		return movieService.getMovieById(id);
	}

	@PostMapping
	public String addMovie(@RequestBody NewMovieRequest movie) {
		return movieService.addMovie(movie);
	}
	
	@GetMapping(value = "/all")
	public List<MovieResponse> getAllMovies() {
		return movieService.getAllMovies();
	}

	@DeleteMapping("/{movieId}")
	public void deleteMovie(@PathVariable("movieId") Integer id) {
		movieService.deleteMovie(id);
	}

	@PutMapping("/{movieId}")
	public void updateMovie(@PathVariable("movieId") Integer id, @RequestBody NewMovieRequest movie) {
		movieService.updateMovie(id, movie);
	}
}
