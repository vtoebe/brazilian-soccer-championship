package com.letscode.brazilian_soccer_championship;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static Set<Game> games = new HashSet<>();
    public static Set<Team> teams = new HashSet<>();

    public static final String RANKING_DIR = "src/main/resources/ranking/";
    public static final String TEAMS_DIR = "src/main/resources/teams/";
    public static final String CHAMPIONSHIP_FILE_PATH = "src/main/resources/brazilian-soccer-championship-results.csv";

    public static void main(String[] args) {
        fileReader(CHAMPIONSHIP_FILE_PATH);

        getTeamsList();
        setTeams();

        Ranking ranking = new Ranking(teams);

        generateDir(TEAMS_DIR);
        writeTeamFile();

        generateDir(RANKING_DIR);
        writeRankingFile(ranking.generateRanking());

        System.out.println("terminou");
    }


    private static void fileReader(String path) {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(path, UTF_8))) {
            String line;
            while ((line = buffReader.readLine()) != null) {
                getCsvData(line);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
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

    private static void generateDir(String dir) {
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
