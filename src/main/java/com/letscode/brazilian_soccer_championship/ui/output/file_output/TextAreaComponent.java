package com.letscode.brazilian_soccer_championship.ui.output.file_output;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;

public class TextAreaComponent extends JPanel {
    JTextArea textArea;
    JScrollPane txtAreaScrollPane;

    public TextAreaComponent(){
        setTextArea();
        this.setLayout(new GridLayout());
        add(txtAreaScrollPane);
    }

    private void setTextArea() {
        textArea = new JTextArea(45, 90);
        textArea.setMargin(new Insets(5,5,5,5));
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(-1, 700));
        textArea.setLayout(new GridLayout());
        txtAreaScrollPane = new JScrollPane(textArea);
    }

    public void readFile(String filename) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename)));
            textArea.read(input, "reading file");
            System.out.println(input);
        } catch (IOException ignored) {
        }
    }
}
