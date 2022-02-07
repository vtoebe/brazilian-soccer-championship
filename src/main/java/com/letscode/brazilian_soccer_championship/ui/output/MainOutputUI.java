package com.letscode.brazilian_soccer_championship.ui.output;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;

import javax.swing.*;
import java.util.Set;

public class MainOutputUI {
    Set<Team> teams;
    Set<Game> games;
    Ranking ranking;

    private JTabbedPane tabbedPane;

    MainOutputUI(Set<Game> games, Set<Team> teams, Ranking ranking){
        this.teams = teams;
        this.games = games;
        this.ranking = ranking;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    private void createUIComponents() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Times", new TeamUI(teams).getPanel());
    }
}
