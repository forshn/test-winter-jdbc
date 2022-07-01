package ru.forsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.forsh.dao.MovieDao;
import ru.forsh.model.Movie;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MovieService {

    private final MovieDao movieDao;

    @Autowired
    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public void addNewMovie(Movie movie) {
        movieDao.insertMovie(movie);

      //  movieDao.insertMovie(movie);
    }




    public List<Movie> getMovies() {
        List<Movie> movies = movieDao.selectMovies();
        return movies.isEmpty() ? Collections.emptyList() : movies;
    }


    public void deleteMovie(Long id) {
        Optional<Movie> movies = movieDao.selectMovieById(id);
        movies.ifPresent(
                movie -> {
                    int result = movieDao.deleteMovie(id);
                    if (result != 1) {
                        throw new IllegalStateException("couldn't delete movie");
                    }
                }
        );
    }

    public Movie getMovie(Long id) {
        return movieDao.selectMovieById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("movie with id %s not found", id)));
    }

}
