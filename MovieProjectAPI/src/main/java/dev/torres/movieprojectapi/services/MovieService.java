package dev.torres.movieprojectapi.services;

import dev.torres.movieprojectapi.entities.Movie;
import dev.torres.movieprojectapi.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(){}
    @Autowired
    public MovieService(MovieRepository movieRepo){
        this.movieRepository = movieRepo;
    }

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String id){
        Optional<Movie> responseMovie = movieRepository.findMovieByImdbId(id);

        return responseMovie;
    }
}
