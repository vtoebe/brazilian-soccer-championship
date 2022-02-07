package com.letscode.brazilian_soccer_championship.ui.input;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Set;

public class ChooserFile extends JPanel{
    private JButton button1;
    private JTextPane textPane1;
    private JPanel pane;
    private final JFileChooser chooser;

    public ChooserFile() {
        chooser = new JFileChooser("./src/main/resources");
        chooser.setFileFilter(new FileNameExtensionFilter("Only .txt file", "txt"));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionButton();
            }
        });
    }

    public void actionButton(){
        int returnVal = chooser.showOpenDialog(ChooserFile.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file)));
                textPane1.read(input, "READING FILE :-)");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Operation is CANCELLED :(");
        }
    }
}
