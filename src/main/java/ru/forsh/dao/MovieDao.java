package ru.forsh.dao;

import ru.forsh.model.Movie;
import ru.protei.winter.jdbc.JdbcDAO;

import java.util.List;
import java.util.Optional;


public interface MovieDao extends JdbcDAO<Long, Movie> {
    List<Movie> selectMovies();
    int insertMovie(Movie movie);
    int deleteMovie(Long id);
    Optional<Movie> selectMovieById(Long id);
    Optional<Movie> selectMovieByName(String name);
}
