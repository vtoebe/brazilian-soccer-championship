package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;

import java.io.*;
import java.text.ParseException;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.*;

public class FileService {
    public static final String CHAMPIONSHIP_FILE_PATH = "src/main/resources/santander811matchesResult.csv";
    
    public static Set<Game> getGamesFromFile() throws IOException, ParseException {
        return readLines(new File(CHAMPIONSHIP_FILE_PATH), UTF_8).stream().
                map(GameService::buildGameFromLineFile).
                filter(Objects::nonNull).
                collect(Collectors.toSet());
    }

    public static Set<Game> getGamesFromFile(File csvFile) throws IOException {
        return readLines(csvFile, UTF_8).stream().
                map(GameService::buildGameFromLineFile).
                filter(Objects::nonNull).
                collect(Collectors.toSet());
    }

    public static <E> void writeFile(Collection<E> source, String dir, String file, String header) {
        try {
            forceMkdir(new File(dir));
            File writingFile = new File(dir + file);
            write(writingFile, header, UTF_8,false);
            writeLines(writingFile, source,"" , true);
        } catch (IOException e) { e.printStackTrace(); }
    }

}
