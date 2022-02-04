package com.letscode.brazilian_soccer_championship;

import com.letscode.brazilian_soccer_championship.entities.Game;
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
    public static Set<Team> teams = new HashSet<>();

    public static final String RANKING_DIR = "src/main/resources/ranking/";
    public static final String TEAMS_DIR = "src/main/resources/teams/";
    public static final String CHAMPIONSHIP_FILE_PATH = "src/main/resources/brazilian-soccer-championship-results.csv";

    public static void main(String[] args) {
        try {
            games = getGamesFromFile(CHAMPIONSHIP_FILE_PATH);
            teams = getAllTeams(games);
            System.out.println(games);
            System.out.println(teams);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

//        getTeamsList();
//        setTeams();
//
//        Ranking ranking = new Ranking(teams);
//
//        generateDir(TEAMS_DIR);
//        writeTeamFile();
//
//        generateDir(RANKING_DIR);
//        writeRankingFile(ranking.generateRanking());

        System.out.println("terminou");
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
