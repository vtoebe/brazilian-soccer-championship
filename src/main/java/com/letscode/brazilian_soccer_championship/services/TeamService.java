package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Team;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TeamService {

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
        teams.forEach(team -> setTeamGames(team, games));
    }

    public static void writeTeamFile(Team team) {
        FileService.writeFile(team.getGames(), team.getDIR(), team.getFILENAME(), team.getHEADER());
    }

    public static void writeAllTeamFiles(Set<Team> teams) {
        teams.forEach(TeamService::writeTeamFile);
    }

}
