package ru.forsh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.protei.winter.jdbc.annotations.*;

@JdbcEntity(table = "actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    @JdbcId
    private Long id;
    @JdbcColumn(name = "name")
    private String name;

    @JdbcColumn(name = "movie")
    private Long movie;
    public Actor(String name) {
        this.name = name;
    }
}
