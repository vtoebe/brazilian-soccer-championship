package com.letscode.brazilian_soccer_championship.ui.output.team_output;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Team;
import com.letscode.brazilian_soccer_championship.ui.output.CustomIcon;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class TeamGameUI {
    private final static Map<String, ImageIcon> iconMap= new HashMap<>();
    private  Game game;
    private  Team team;

    private JPanel pane;
    private JLabel teamLabel1;
    private JLabel teamLabel2;
    private JLabel scoreLabel1;
    private JLabel scoreLabel2;
    private JLabel shieldLabel1;
    private JLabel shieldLabel2;
    private JLabel dateLabel;

    public TeamGameUI(Game game, Team team) {
        this.game = game;
        this.team = team;

        resetPanelBackground();
        resetFonts();
    }

    public JPanel getPane() {
        return pane;
    }

    private void createUIComponents() {
        teamLabel1 = new JLabel("<html> <p align=\"center\">" + game.getHome() + " </p></html>");
        teamLabel2 = new JLabel("<html> <p align=\"center\">" + this.game.getVisitor() + " </p></html>");
        scoreLabel1 = new JLabel(""+this.game.getHomeScore());
        scoreLabel2 = new JLabel(""+this.game.getVisitorScore());
        shieldLabel1 = new JLabel(getTeamShield(this.game.getHome()));
        shieldLabel2 = new JLabel(getTeamShield(this.game.getVisitor()));
        dateLabel = new JLabel(new SimpleDateFormat("dd/MM/yyyy").format(this.game.getDate()));
    }

    private void resetPanelBackground(){
        if(team != null){
            String winner = game.getWinner();

            if(winner != null){
                if (winner.equals(team.getName())){
                    this.pane.setBackground(new Color(89, 196, 55));
                } else{
                    this.pane.setBackground(new Color(220, 86, 86));
                }
            }
        }
    }

    private void resetFonts() {
        int comparator = this.game.getHomeScore() - this.game.getVisitorScore();
        Font currentFont = scoreLabel1.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.3f);

        if(comparator > 0){
            scoreLabel1.setFont(newFont);
            teamLabel1.setFont(newFont);
        }else  if (comparator < 0){
            scoreLabel2.setFont(newFont);
            teamLabel2.setFont(newFont);
        }
    }

    private ImageIcon getTeamShield(String teamName){
        if (!iconMap.containsKey(teamName)) {
            ImageIcon icon = CustomIcon.buildTeamIcon(teamName, 40, 40);
            iconMap.put(teamName, icon);
            return icon;
        } else {
            return iconMap.get(teamName);
        }
    }
}
