package com.letscode.brazilian_soccer_championship;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;
import com.letscode.brazilian_soccer_championship.ui.output.OutputUI;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import static com.letscode.brazilian_soccer_championship.services.FileService.getGamesFromFile;
import static com.letscode.brazilian_soccer_championship.services.FileService.writeFile;
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
        ArrayList<Team> teamRanking = ranking.getRanking();
        ranking.setTeamRankingPosition(teamRanking);

        writeFile(teamRanking, ranking.getDIR(), ranking.getFILENAME(), ranking.getHEADER());
        writeAllTeamFiles(teams);

        OutputUI.show(games, teams, ranking);
    }

}
