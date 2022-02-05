package com.letscode.brazilian_soccer_championship.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Comparator.comparing;

@Data
@EqualsAndHashCode
public class Team {
    private String name;
    private int wins;
    private int ties;
    private int losses;
    private int score;
    private ArrayList<Game> games = new ArrayList<>();

    private String HEADER = "   DATA    | MANDANTE    x    VISITANTE\n";
    private String DIR = "src/main/resources/teams/";
    private String FILENAME;


    public Team(String name) {
        this.name = name;
        this.FILENAME = name + ".txt";
    }

    public void addGames(Game game) {
        games.add(game);
    }

    public ArrayList<Game> getGames() {
        games.sort(comparing(Game::getDate)
                .thenComparing(Game::getHome)
                .thenComparing(Game::getVisitor));
        return games;
    }

    public void checkData() {
        for (Game game : games) {
            if (game.getHomeScore() == game.getVisitorScore()) {
                ties++;
            } else if (Objects.equals(game.getHome(), name) && game.getHomeScore() > game.getVisitorScore()
                    || Objects.equals(game.getVisitor(), name) && game.getHomeScore() < game.getVisitorScore()){
                wins++;
            } else losses++;
        }
    }

    public void setScore() { this.score = (wins * 3) + ties; }

    @Override
    public String toString() {
        return name + ";"
                + wins + ";"
                + ties + ";"
                + losses + ";"
                + score + "\n";
    }
}
