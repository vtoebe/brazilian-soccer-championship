package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Team;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

//    public static Set<Team> getAllTeams(Set<Game> games){
//        Map<String, Team> teamsMap = new HashMap<>();
//
//        games.stream().forEach(game -> {
//            if(teamsMap.containsKey(game.getHome())){
//                teamsMap.get(game.getHome())
//                        .getGames()
//                        .add(game);
//            }else{
//                Team team = new Team(game.getHome());
//                team.getGames().add(game);
//                teamsMap.put(game.getHome(),team);
//            }
//            if(teamsMap.containsKey(game.getVisitor())){
//                teamsMap.get(game.getVisitor())
//                        .getGames()
//                        .add(game);
//            }else{
//                Team team = new Team(game.getVisitor());
//                team.getGames().add(game);
//                teamsMap.put(game.getVisitor(),team);
//            }
//        });
//
//        return new HashSet<>(teamsMap.values());
//    }
}
