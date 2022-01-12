package ru.kubsu.database;

import androidx.annotation.NonNull;

public class Gamer {
    private long id;
    private String name;
    private long score;

    public Gamer(long id, String name, long score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + " : " + this.score;
    }
}
