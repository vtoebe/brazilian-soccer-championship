package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Team;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

import static com.letscode.brazilian_soccer_championship.Main.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {

    public static void fileReader(String path) {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(path, UTF_8))) {
            String line;
            while ((line = buffReader.readLine()) != null) {
                getCsvData(line);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void generateDir(String dir) {
        new File(dir).mkdir();
    }

    public static void writeTeamFile(){
        for (Team team: teams){
            try {
                FileWriter file = new FileWriter(TEAMS_DIR+team.getDir(), true);
                PrintWriter fileWriter = new PrintWriter(file);

                for (Game game : team.getGames()){
                    fileWriter.print(game);
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeRankingFile(ArrayList<Team> ranking){
        try {
            FileWriter file = new FileWriter(RANKING_DIR+"ranking.txt", true);
            PrintWriter fileWriter = new PrintWriter(file);

            fileWriter.print(" P | V  | E  | D  | Time\n");

            for (Team ranked : ranking){
                fileWriter.print(ranked);
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
