package com.letscode.brazilian_soccer_championship;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.letscode.brazilian_soccer_championship.services.FileManager.*;
import static com.letscode.brazilian_soccer_championship.services.TeamService.getAllTeams;

public class Main {
    public static Set<Game> games;
    public static Set<Team> teams;

    public static void main(String[] args) {
        try {
            games = getGamesFromFile();
            teams = getAllTeams(games);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        setTeams();

        Ranking ranking = new Ranking(teams);

        writeTeamFile(teams);
        writeRankingFile(ranking.generateRanking());
    }


    public static void setTeams(){
        for (Team team : teams){
            for (Game game : games){
                if (Objects.equals(game.getHome(), team.getName()) || Objects.equals(game.getVisitor(), team.getName())){
                    team.setGames(game);
                }
            }
            team.checkData();
            team.setScore();
        }
    }

}
