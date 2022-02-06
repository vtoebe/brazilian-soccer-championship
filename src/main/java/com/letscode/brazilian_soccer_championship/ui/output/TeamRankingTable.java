package com.letscode.brazilian_soccer_championship.ui.output;

import com.letscode.brazilian_soccer_championship.entities.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class TeamRankingTable extends JTable{
    private static String[] rankinHeader = {"P", "V", "E", "D", "GP", "GC", "SG"};
    private final Color[] rankinColor = {
            new Color(150, 185, 253), new Color(89, 252, 89),
            new Color(222, 222, 222), new Color(246, 133, 133),
            Color.black, Color.black, Color.black};

    public TeamRankingTable(Team team) {
        super();
        resetTableModel(team);
        renderColumnModel();
        renderHeader();
    }

    private void resetTableModel(Team team){
        Integer[][] rankingData = {{team.getScore(), team.getWins(), team.getTies(), team.getLosses(),
                team.getGoalsFor(), team.getGoalsAgainst(), team.getGoalsFor() - team.getGoalsAgainst()}};
        TableModel model = new DefaultTableModel(rankingData, rankinHeader);
        this.setModel(model);
    }

    private void renderColumnModel() {
        Font currentFont = this.getFont();
        Font newFont = currentFont.deriveFont(70f).deriveFont(Font.PLAIN);
        this.setFont(newFont);
        for (int i = 0; i < 7; i++) {
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            cellRenderer.setForeground(rankinColor[i]);
            this.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

    }

    private void renderHeader() {
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.getTableHeader().setDefaultRenderer(headerRenderer);

    }
}