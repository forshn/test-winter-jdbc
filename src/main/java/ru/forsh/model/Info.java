package ru.forsh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.protei.winter.jdbc.annotations.JdbcColumn;

@Data
@AllArgsConstructor
public class Info {

    @JdbcColumn(name = "description")
    private String description;
}
