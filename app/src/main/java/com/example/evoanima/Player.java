package com.example.evoanima;
import java.io.Serializable;

public class Player implements Serializable {
    public static final String PLAYER_NAME = "player_name";
    public static final String ANIMA = "anima";

    public Anima anima;
    public String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Anima getAnima() {
        return anima;
    }

    public void setAnima(Anima anima) {
        this.anima = anima;
    }
}
