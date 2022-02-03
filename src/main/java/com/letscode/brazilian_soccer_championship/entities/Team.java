package com.letscode.brazilian_soccer_championship.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Data;

import java.util.*;

import static java.util.Comparator.*;

@Data
@Getter
@EqualsAndHashCode
public class Team implements Comparable<Team>{
    private String name;
    private int wins;
    private int ties;
    private int losses;
    private int score;
    private String dir;
    private Set<Game> games = new TreeSet<>(comparing(Game::getDate));

    public Team(String name) {
        this.name = name;
        this.dir = name + ".txt";
    }

    public Team(){}

    public void setGames(Game game){
        games.add(game);
    }

    public Set<Game> getGames(){ return games; }

    public void checkData(){
        for (Game game : games){
            if (Objects.equals(game.getHome(), name)){
                if( game.getHomeScore() > game.getVisitorScore()){
                    wins++;
                }
                if (game.getHomeScore() < game.getVisitorScore()){
                    losses++;
                }
            } else if (Objects.equals(game.getVisitor(), name)){
                if( game.getHomeScore() > game.getVisitorScore()){
                    losses++;
                }
                if (game.getHomeScore() < game.getVisitorScore()){
                    wins++;
                }
            }

            if (game.getHomeScore() == game.getVisitorScore()){
                ties++;
            }
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

    @Override
    public int compareTo(Team t){
        return this.getScore() - t.getScore();
    }
}
