package ru.forsh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.protei.winter.jdbc.annotations.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JdbcEntity(table = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @JdbcId(name = "id", idInsertMode = IdInsertMode.AUTO)
    private Long id;
    @JdbcColumn
    private String name;
    @JdbcColumn(name = "release_date")
    private Date releaseDate;

   /* @JdbcOneToMany(table = "rental", localColumn = "id", remoteColumn = "movie")
    private Set<Rental> rental;*/

    @JdbcColumn(name = "geo_type")
    @JdbcEnumerated(EnumType.STRING)
    private GeoType geoType;

    @JdbcEmbed(permType = PermType.FULL)
    private Info info;

   /* @JdbcJoinedObject(joinPath = {
            @JdbcJoinPath(localColumn = "id", remoteColumn = "movie", table = "actor")
    })*/
    @JdbcOneToMany(table = "actor", localColumn = "id", remoteColumn = "movie")
    private Set<Actor> actors = new HashSet<>();

    /*public void addActor(Actor actor){
        this.actors.add(new ActorRef(actor.getId(), actor.getName()));
    }*/

    /*public Set<Long> getActorIds(){
        return this.actors
                .stream()
                .map(ActorRef::getActor)
                .collect(Collectors.toSet());
    }

    public Set<String> getActorNames(){
        return this.actors
                .stream()
                .map(ActorRef::getName)
                .collect(Collectors.toSet());
    }*/
}
