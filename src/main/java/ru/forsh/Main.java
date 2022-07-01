package ru.forsh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.forsh.dao.DaoFactory;
import ru.forsh.dao.MovieDAOimpl;
import ru.forsh.dao.MovieDao;
import ru.forsh.model.*;
import ru.forsh.service.MovieService;
import ru.protei.winter.core.CoreConfigurationContext;
import ru.protei.winter.jdbc.JdbcConfigurationContext;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
        private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {


        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                CoreConfigurationContext.class
                , JdbcConfigurationContext.class
                , DaoFactory.class, MovieService.class);

        MovieService movieService = ctx.getBean(MovieService.class);
        MovieDAOimpl movieDAOimpl = ctx.getBean(MovieDAOimpl.class);

        Set<Actor> actors = new HashSet<>();
        actors.add(new Actor("Dicaprio"));
        actors.add(new Actor("McConaghey"));

        Movie movie = new Movie();
        movie.setName("Green mile");
        movie.setReleaseDate(new Date());
        movie.setActors(actors);
        movie.setGeoType(GeoType.FOREIGN);
        movie.setInfo(new Info("something about this movie"));

        log.debug("------------>>>>>   movieDAOimpl.persist");

     //   movieService.addNewMovie(movie);
        movieDAOimpl.insertMovie(movie);



    }
}