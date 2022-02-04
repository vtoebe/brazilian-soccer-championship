package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Team;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.letscode.brazilian_soccer_championship.Main.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {
    public static final String RANKING_DIR = "src/main/resources/ranking/";
    public static final String TEAMS_DIR = "src/main/resources/teams/";
    
    public static Set<Game> getGamesFromFile(String filePath) throws IOException, ParseException {
        BufferedReader buffReader = new BufferedReader(new FileReader(filePath, UTF_8));
        return buffReader.lines().
                map(FileManager::buildGameFromLineFile).
                filter(Objects::nonNull).
                collect(Collectors.toSet());
    }

    public static Game buildGameFromLineFile(String lineFile) {
        Game game = null;
        String[] splittedLine = lineFile.split(";");

        if (splittedLine.length >= 5){
            try {
                game = Game.builder()    // TODO
                        .home(splittedLine[0])
                        .visitor(splittedLine[1])
                        .homeScore(Integer.parseInt(splittedLine[2]))
                        .visitorScore(Integer.parseInt(splittedLine[3]))
                        .date(new SimpleDateFormat("dd/MM/yyyy").parse(splittedLine[4]))
                        .build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return game;
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
