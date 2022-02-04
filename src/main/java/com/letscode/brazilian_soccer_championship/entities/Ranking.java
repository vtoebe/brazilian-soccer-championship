package com.letscode.brazilian_soccer_championship.entities;

import lombok.*;

import java.util.*;

@Data
public class Ranking {
    public Set<Team> teams;
    private String HEADER = " P | V  | E  | D  | Time\n";
    private String RANKING_DIR = "src/main/resources/ranking/";

    public Ranking(Set<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Team> generateRanking(){
        ArrayList<Team> ranking = new ArrayList<>(teams);
        ranking.sort(Comparator
                .comparing(Team::getScore)
                .thenComparing(Team::getWins)
                .thenComparing(Team::getTies)
                .thenComparing(Team::getLosses)
                .reversed()
                .thenComparing(Team::getName));

        return ranking;
    }
}
