package com.letscode.brazilian_soccer_championship.ui.output;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class OutputUI {
    public static void show(Set<Game> games, Set<Team> teams, Ranking ranking){
        var frame = new JFrame("Brasileirão");
        var mainOutputUI = new MainOutputUI(games, teams, ranking);
        frame.setContentPane(mainOutputUI.getTabbedPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
}
