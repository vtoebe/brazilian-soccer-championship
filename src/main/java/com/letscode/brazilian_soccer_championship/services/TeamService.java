package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Team;
import java.util.*;

public class TeamService {

    private static final String TEAMS_DIR = "src/main/resources/teams/";

    public static Set<Team> getAllTeams(Set<Game> games){
        Set<Team> teams = new HashSet<>();
        games.forEach(game -> {
            teams.add(new Team(game.getHome()));
            teams.add(new Team(game.getVisitor()));
        });
        return teams;
    }

    public static void setTeamGames(Team team, Set<Game> games){
        for (Game game : games){
            if (Objects.equals(game.getHome(), team.getName()) || Objects.equals(game.getVisitor(), team.getName())){
                team.addGames(game);
            }
        }
        team.checkData();
        team.setScore();
    }

    public static void setAllGames(Set<Team> teams, Set<Game> games){
        for (Team team: teams) {
            setTeamGames(team, games);
        }
    }

    public static void writeTeamFile(Team team) {
        FileService.writeFile(team.getGames(), TEAMS_DIR, team.getDir());
    }

    public static void writeAllTeamFiles(Set<Team> teams) {
        for (Team team : teams) {
            writeTeamFile(team);
        }
    }

}
