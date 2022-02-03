package com.letscode.brazilian_soccer_championship.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Data;

import java.util.Date;

@Data
@Getter
@Builder
@EqualsAndHashCode
public class Game {
    private String home;
    private String visitor;
    private int homeScore;
    private int visitorScore;
    private Date date;

    public Game(String home, String visitor, int homeScore, int visitorScore, Date date) {
        this.home = home;
        this.visitor = visitor;
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
        this.date = date;
    }

    @Override
    public String toString() {
        return date + " | "
                + home + " " + homeScore + " x "
                + visitorScore + " " + visitor + "\n";
    }
}
