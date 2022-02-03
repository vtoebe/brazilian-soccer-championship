package com.letscode.brazilian_soccer_championship.entities;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class Ranking {
    public Set<Team> teams;

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
