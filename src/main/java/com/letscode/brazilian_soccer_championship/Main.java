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

    public static void getCsvData(String lineFromFile) throws ParseException {

        if (!Objects.equals(lineFromFile, "")){
            String[] splittedLine = lineFromFile.split(";");

            games.add(Game.builder()
                    .home(splittedLine[0])
                    .visitor(splittedLine[1])
                    .homeScore(Integer.parseInt(splittedLine[2]))
                    .visitorScore(Integer.parseInt(splittedLine[3]))
                    .date(new SimpleDateFormat("dd/MM/yyyy").parse(splittedLine[4]))
                    .build()
            );
        }
    }

    public static void getTeamsList(){
        for(Game game : games){
            teams.add(new Team(game.getHome()));
            teams.add(new Team(game.getVisitor()));
        }
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
