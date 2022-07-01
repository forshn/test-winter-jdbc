package ru.forsh.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.forsh.model.Movie;
import ru.protei.winter.jdbc.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class MovieDAOimpl extends JdbcBaseDAO<Long, Movie> implements MovieDao {



    @Inject
    @Qualifier(value = "Movie")
    private JdbcObjectMapper<Movie> movieJdbcObjectMapper;

    @Autowired
    private JdbcManyRelationsHelper jdbcManyRelationsHelper;

    @Override
    @Qualifier(JdbcMultiConfigurationRegistrator.JDBC_TEMPLATE_PREFIX + "domain")
    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        System.out.println("SET JDBC TEMPLATE " + this.getClass().getName());
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql = "select name, release_date, geo_type from movie order by name limit 10";
        return jdbcTemplate.query(sql, movieJdbcObjectMapper);
    }

    @Override
    public int insertMovie(Movie movie) {
        if (selectMovieById(movie.getId()).isPresent() || selectMovieByName(movie.getName()).isPresent()) {
            throw new IllegalStateException("movie already exists");
        }
        persist(movie);
        jdbcManyRelationsHelper.persistAll(movie);
      //  jdbcTemplate.update("insert into movie(name, release_date, description, geo_type) VALUES (?,?,?,?)", movie);

        return 1;
    }

    @Override
    public int deleteMovie(Long id) {
        String sql = "delete from movie where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Movie> selectMovieById(Long id) {
        String sql = "select id, name, release_date from movie where id = ?";
        return jdbcTemplate.query(sql, movieJdbcObjectMapper, id)
                .stream().findFirst();
    }

    @Override
    public Optional<Movie> selectMovieByName(String name) {
        String sql = "select id, name, release_date from movie where name = ?";
        return jdbcTemplate.query(sql, movieJdbcObjectMapper, name)
                .stream().findFirst();
    }

}
