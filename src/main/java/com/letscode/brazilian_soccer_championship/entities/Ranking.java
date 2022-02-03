package com.letscode.brazilian_soccer_championship.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Data;

import java.util.*;

//@Data
////@Getter
//@Builder
//@ToString
//@EqualsAndHashCode
public class Ranking {
    public Set<Team> teams;

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
