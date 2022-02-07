package com.letscode.brazilian_soccer_championship;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;
import com.letscode.brazilian_soccer_championship.ui.input.InputFile;
import com.letscode.brazilian_soccer_championship.ui.input.InputUI;
import com.letscode.brazilian_soccer_championship.ui.output.OutputUI;

import javax.swing.*;
import java.io.File;
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
            games = getGamesFromFile(InputUI.inputCSV());
            teams = getAllTeams(games);

            setAllGames(teams, games);
            Ranking ranking = new Ranking(teams);
            ArrayList<Team> teamRanking = ranking.getRanking();
            ranking.setTeamRankingPosition(teamRanking);

            writeFile(teamRanking, ranking.getDIR(), ranking.getFILENAME(), ranking.getHEADER());
            writeAllTeamFiles(teams);

            OutputUI.show(games, teams, ranking);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NullPointerException e){
            System.out.println("Cancelado");
        }
    }

}
