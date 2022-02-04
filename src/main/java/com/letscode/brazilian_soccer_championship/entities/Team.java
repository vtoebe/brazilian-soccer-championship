package com.letscode.brazilian_soccer_championship.entities;

import lombok.EqualsAndHashCode;
import lombok.Data;

import java.util.*;

import static java.util.Comparator.*;

@Data
@EqualsAndHashCode
public class Team{
    private String name;
    private int wins;
    private int ties;
    private int losses;
    private int score;
    private Set<Game> games = new TreeSet<>(comparing(Game::getDate));

    private String HEADER = "   DATA    | MANDANTE    x    VISITANTE\n";
    private String DIR = "src/main/resources/teams/";
    private String FILENAME;


    public Team(String name) {
        this.name = name;
        this.FILENAME = name + ".txt";
    }

    public void addGames(Game game){
        games.add(game);
    }

    public Set<Game> getGames(){ return games; }

    public void checkData(){
        for (Game game : games){
              if (game.getHomeScore() == game.getVisitorScore()){
                  ties++;
              } else if (Objects.equals(game.getHome(), name) && game.getHomeScore() > game.getVisitorScore()
               || Objects.equals(game.getVisitor(), name) && game.getHomeScore() < game.getVisitorScore()){
                  wins++;
              } else losses++;
        }
    }

    public void setScore(){
        this.score =  (wins * 3) + ties;
    }

    @Override
    public String toString() {
        return score + " | "
                + String.format("%02d", wins) + " | "
                + String.format("%02d", ties) + " | "
                + String.format("%02d", losses) + " | "
                + name + "\n";
    }
}
