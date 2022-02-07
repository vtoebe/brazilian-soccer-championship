package com.letscode.brazilian_soccer_championship.ui.output.team_output;

import com.letscode.brazilian_soccer_championship.entities.Team;
import com.letscode.brazilian_soccer_championship.ui.shared.CustomIcon;

import javax.swing.*;

public class TeamInfoUI {
    private Team team;
    private JPanel pane;

    private JLabel positionRankLabel;
    private JLabel teamIcon;
    private JLabel teamNameLabel;
    private JTable teamRankingTable;

    public TeamInfoUI(Team team){
        this.team = team;
    }

    public JPanel getPane() {
        return pane;
    }

    private void createUIComponents() {
        ImageIcon ballIcon = CustomIcon.buildBallIcon(getBallColor(), 70, 70);
        positionRankLabel = new JLabel("" + team.getRankingPosition(), ballIcon, JLabel.TRAILING);

        teamNameLabel = new JLabel(team.getName());

        ImageIcon icon = CustomIcon.buildTeamIcon(team.getName(), 80, 80);
        teamIcon = new JLabel(icon);

        teamRankingTable = new TeamRankingTable(team);
    }

    private String getBallColor() {
        String ballColor = "blue";
        if(team.getRankingPosition() <= 4){
            ballColor = "green";
        } else if (team.getRankingPosition() > 16){
            ballColor = "red";
        }
        return ballColor;
    }
}
