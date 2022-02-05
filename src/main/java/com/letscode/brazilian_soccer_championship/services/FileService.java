package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;

import java.io.*;
import java.text.ParseException;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileService {
    public static final String CHAMPIONSHIP_FILE_PATH = "src/main/resources/santander811matchesResult.csv";
    
    public static Set<Game> getGamesFromFile() throws IOException, ParseException {
        BufferedReader buffReader = new BufferedReader(new FileReader(CHAMPIONSHIP_FILE_PATH, UTF_8));
        return buffReader.lines().
                map(GameService::buildGameFromLineFile).
                filter(Objects::nonNull).
                collect(Collectors.toSet());
    }

    public static void generateDir(String dir) { new File(dir).mkdir(); }

    public static <E> void writeFile(Collection<E> source, String dir, String file, String header){
        generateDir(dir);
        try {
            PrintWriter fileWriter = new PrintWriter(new FileWriter(dir + file, UTF_8, false));
            fileWriter.print(header);
            source.forEach(fileWriter::print);
            fileWriter.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

}
