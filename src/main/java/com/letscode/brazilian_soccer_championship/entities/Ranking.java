package com.letscode.brazilian_soccer_championship.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

@Data
public class Ranking {
    public Set<Team> teams;

    private String HEADER = "Time;Vitorias;Empates;Derrotas;Pontos\n";
    private String DIR = "src/main/resources/ranking/";
    private String FILENAME = "ranking.csv";

    public Ranking(Set<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Team> getRanking(){
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

    public void setTeamRankingPosition(ArrayList<Team> ranking){
        for (int i = 0; i<ranking.size(); i++){
            ranking.get(i).setRankingPosition(i+1);
        }
    }
}
