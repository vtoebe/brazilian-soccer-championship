package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileService {
    public static final String CHAMPIONSHIP_FILE_PATH = "src/main/resources/brazilian-soccer-championship-results.csv";
    
    public static Set<Game> getGamesFromFile() throws IOException, ParseException {
        BufferedReader buffReader = new BufferedReader(new FileReader(CHAMPIONSHIP_FILE_PATH, UTF_8));
        return buffReader.lines().
                map(FileService::buildGameFromLineFile).
                filter(Objects::nonNull).
                collect(Collectors.toSet());
    }

    public static Game buildGameFromLineFile(String lineFile) {
        Game game = null;
        String[] splitLine = lineFile.split(";");

        if (splitLine.length >= 5){
            try {
                game = Game.builder()    // TODO
                        .home(splitLine[0])
                        .visitor(splitLine[1])
                        .homeScore(Integer.parseInt(splitLine[2]))
                        .visitorScore(Integer.parseInt(splitLine[3]))
                        .date(( new SimpleDateFormat("dd/MM/yyyy").parse(splitLine[4])))
                        .build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return game;
    }

    public static void generateDir(String dir) { new File(dir).mkdir(); }

    public static <E> void writeFile(Collection<E> source, String dir, String file, String header){
        generateDir(dir);
        try {
            PrintWriter fileWriter = new PrintWriter(new FileWriter(dir + file, false));
            fileWriter.print(header);
            source.forEach(fileWriter::print);
            fileWriter.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

}
