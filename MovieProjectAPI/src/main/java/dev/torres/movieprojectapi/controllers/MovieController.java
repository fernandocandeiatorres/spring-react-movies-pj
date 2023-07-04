package dev.torres.movieprojectapi.controllers;

import dev.torres.movieprojectapi.entities.Movie;
import dev.torres.movieprojectapi.services.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "http://localhost:3000/")
public class MovieController {
    private MovieService movieService;
    public MovieController(){}

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable String imdbId){
        Optional<Movie> responseMovie = movieService.getMovieById(imdbId);
        if(responseMovie.isPresent()){
            return new ResponseEntity<Optional<Movie>>(movieService.getMovieById(imdbId), HttpStatus.OK);
        } else {
            throw new RuntimeException("Movie not found");
        }
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(RuntimeException exc){
//        ErrorResponse error = new ErrorResponseException();
//    }
}
