package com.letscode.brazilian_soccer_championship.ui.output;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;

import javax.swing.*;
import java.util.Set;

public class OutputUI {
    public static void show(Set<Game> games, Set<Team> teams, Ranking ranking){
        JFrame gamesByTeam = TeamUI.build(teams);
        gamesByTeam.setVisible(true);

    }
}
