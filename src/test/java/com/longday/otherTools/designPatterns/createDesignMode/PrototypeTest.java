package com.longday.otherTools.designPatterns.createDesignMode;

import com.google.gson.Gson;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/6
 */
public class PrototypeTest {
    @Test
    public void prototype() throws CloneNotSupportedException {
        RealizeType realizeType = new RealizeType();
        RealizeType clone = realizeType.clone();
        System.out.println(clone == realizeType);

        //使用Spring BeanUtils
        Actor actor = new Actor();
        actor.setName("Julia Butters");
        Movie movie = new Movie();
        movie.setActor(actor);

        Movie newMovie = new Movie();
        BeanUtils.copyProperties(movie, newMovie);

        System.out.println(movie.getActor() == newMovie.getActor()); //true

        //使用Cglib BeanCopier 同上

    }

    @Test
    public void prototypeDeep() {
        Actor actor = new Actor();
        actor.setName("Julia Butters");
        Movie movie = new Movie();
        movie.setActor(actor);

        //Gson
        Gson gson = new Gson();
        Movie newMovie = gson.fromJson(gson.toJson(movie), Movie.class);
        newMovie.getActor().setName("新");
        System.out.println(movie.getActor().getName());
        System.out.println(newMovie.getActor().getName());
        System.out.println(movie.getActor() == newMovie.getActor()); //true


    }
}

class RealizeType implements Cloneable {
    public RealizeType() {
        System.out.println("无参构造方法");
    }

    @Override
    public RealizeType clone() throws CloneNotSupportedException {
        return (RealizeType) super.clone();
    }
}

@Data
class Movie {
    private Actor actor;

}

@Data
class Actor {
    private String name;
}
