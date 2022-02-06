package com.letscode.brazilian_soccer_championship.ui.output;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamUI {
    private final Set<Team> teams;
    private JPanel panel;
    private JList<Object> teamList;
    private JPanel teamGames;
    private JScrollPane scroll;
    private JPanel infoTeamPanel;
    private JPanel gamePanel;


    public TeamUI(Set<Team> teams) {
        this.teams = teams;
    }

    public static JFrame build(Set<Team> teams) {
        JFrame frame = new JFrame("Game by Team");
        frame.setContentPane(new TeamUI(teams).panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);

        return frame;
    }

    private void createUIComponents() {
        List<Team> sortedTeams = teams.stream().
                sorted(Comparator.comparing(Team::getName)).
                collect(Collectors.toList());
        List<Object> teamNames = sortedTeams.stream().map(Team::getName).collect(Collectors.toList());
        DefaultListModel<Object> listModel = new DefaultListModel<>();
        listModel.addAll(teamNames);
        teamList = new JList<>(listModel);
        teamList.setBorder(new EmptyBorder(10,10, 10, 10));
        teamList.addListSelectionListener(e -> {
            if(e.getValueIsAdjusting()){
                int index = teamList.getSelectedIndex();
                revalidateTeamGamesUI(sortedTeams, index);
            }
        });


        teamGames = new JPanel();
        teamGames.setLayout(new BoxLayout(teamGames, BoxLayout.Y_AXIS));
        teamGames.setPreferredSize(new Dimension(teamGames.getWidth(),5000));

        infoTeamPanel = new JPanel();
        infoTeamPanel.setLayout(new BoxLayout(infoTeamPanel, BoxLayout.Y_AXIS));

        scroll = buildScroll();
    }


    private JScrollPane buildScroll() {
        JScrollPane scrollPane = new JScrollPane(teamGames);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(25, 64, 19)));
        UIManager.put("ScrollBar.track", new ColorUIResource(new Color(74, 191, 57)));
        UIManager.put("ScrollBar.width", 9);

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        return scrollPane;
    }

    private void revalidateTeamGamesUI(List<Team> sortedTeams, int index) {
        teamGames.removeAll();
        infoTeamPanel.removeAll();

        Team team = sortedTeams.get(index);
        infoTeamPanel.add(new TeamInfoUI(team).getPane());
        for(Game game: team.getGames()){
            TeamGameUI teamGameUI = new TeamGameUI(game, team);
            teamGames.add(teamGameUI.getPane());
        }

        teamGames.repaint();
        infoTeamPanel.repaint();
        infoTeamPanel.revalidate();
        scroll.revalidate();
        scroll.getVerticalScrollBar().setValue(0);
    }
}
