package com.showtime.showtime.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.showtime.entities.requestEntities.MovieAdd;
import com.showtime.showtime.entities.responseEntities.MoviesWithActiveBooking;
import com.showtime.showtime.services.MovieService;
import com.showtime.showtime.utils.ApiResponse;
import com.showtime.showtime.utils.ResponseHandler;

import io.jsonwebtoken.lang.Collections;
import jakarta.validation.Valid;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping("/admin/movies/new")
    public ResponseEntity<ApiResponse> addNewMovie(@Valid @RequestBody MovieAdd newMovie) {
        try {
            movieService.addNewMovie(newMovie);
            return responseHandler.successResponse(Collections.emptyMap(), "Successfully added a movie", 200);
        } catch (Exception error) {
            return responseHandler.errorResponse(error, "Failed to create movie. Please try again after sometime.", 404);
        }
    }

    @PutMapping("/admin/movies/{movieId}/status")
    public ResponseEntity<ApiResponse> changeBookingStatus(@RequestParam Boolean activate, @PathVariable String movieId) {
        try {
            movieService.updateMovieBookingStatus(movieId, activate);
            return responseHandler.successResponse(Collections.emptyMap(), "Successfully changed the booking status", 200);
        } catch (Exception error) {
            return responseHandler.errorResponse(error, "Failed to change status. Please try again after sometime.", 404);
        }
    }

    @GetMapping("/auth/movies/active-booking")
    public ResponseEntity<ApiResponse> getMoviesAvailableToBook() {
        try {
            List<MoviesWithActiveBooking> moviesList = movieService.getMoviesListWithActiveBooking();
            return responseHandler.successResponse(moviesList, "Successfully fetched list of movies", 200);
        } catch (Exception error) {
            return responseHandler.errorResponse(error, "Failed to fetch movies. Please try again after sometime.", 404);
        }
    }

    @GetMapping("/auth/movies/{movieId}/details")
    public ResponseEntity<ApiResponse> getMovieDetailsById(@PathVariable String movieId) {
        try {
            Object movieDetails = movieService.getMovieDetails(movieId);
            return responseHandler.successResponse(movieDetails, "Successfully fetched movie details", 200);
        } catch (Exception error) {
            return responseHandler.errorResponse(error, "Failed to fetch movie details. Please try again after sometime.", 404);
        }
    } 
}
