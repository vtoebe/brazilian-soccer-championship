package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Team;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import static com.letscode.brazilian_soccer_championship.Main.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {
    public static final String RANKING_DIR = "src/main/resources/ranking/";
    public static final String TEAMS_DIR = "src/main/resources/teams/";

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

    public static void generateDir(String dir) { new File(dir).mkdir(); }

    public static void writeTeamFile(Set<Team> teams) {
        generateDir(TEAMS_DIR);
        for (Team team : teams) {
            try {
                PrintWriter fileWriter = new PrintWriter(new FileWriter(TEAMS_DIR + team.getDir(), true));
                team.getGames().forEach(fileWriter::print);
                fileWriter.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public static void writeRankingFile(ArrayList<Team> ranking) {
        generateDir(RANKING_DIR);
        try {
            PrintWriter fileWriter = new PrintWriter(new FileWriter(RANKING_DIR + "ranking.txt", true));
            fileWriter.print(" P | V  | E  | D  | Time\n");
            ranking.forEach(fileWriter::print);
            fileWriter.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
