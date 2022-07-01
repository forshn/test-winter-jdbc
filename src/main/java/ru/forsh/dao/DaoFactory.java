package ru.forsh.dao;

import org.springframework.context.annotation.Bean;

public class DaoFactory {

    @Bean
    public MovieDao getMovieDAO() {
        return new MovieDAOimpl();
    }
/*
    @Bean
    public ActorDao getActorDAO() {
        return new ActorDaoImpl();
    }*/
}
