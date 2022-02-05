package com.letscode.brazilian_soccer_championship.services;

import com.letscode.brazilian_soccer_championship.entities.Game;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GameService {
    public static Game buildGameFromLineFile(String lineFile) {
        Game game = null;
        String[] splitLine = lineFile.split(";");

        if (splitLine.length >= 5){
            try {
                game = Game.builder()
                        .home(splitLine[0])
                        .visitor(splitLine[1])
                        .homeScore(Integer.parseInt(splitLine[2]))
                        .visitorScore(Integer.parseInt(splitLine[3]))
                        .date(( new SimpleDateFormat("yyyy-MM-dd").parse(splitLine[4])))
                        .build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return game;
    }
}
