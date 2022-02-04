package com.letscode.brazilian_soccer_championship;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;

import java.io.IOException;
import java.text.ParseException;
import java.util.Set;

import static com.letscode.brazilian_soccer_championship.services.FileService.*;
import static com.letscode.brazilian_soccer_championship.services.TeamService.*;

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

        setAllGames(teams, games);

        Ranking ranking = new Ranking(teams);

        writeFile(ranking.generateRanking(), ranking.getRANKING_DIR(), "ranking.txt", ranking.getHEADER());
        writeAllTeamFiles(teams);
    }

}
