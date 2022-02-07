package com.letscode.brazilian_soccer_championship.ui.output.file_output;

import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileUI extends JFrame {
    TreeComponent treeComponent = new TreeComponent();
    TableComponent tableComponent = new TableComponent();
    TextAreaComponent textAreaComponent = new TextAreaComponent(treeComponent.tree);
    static JTree tree;

    // panels
    JPanel cardPanel = new JPanel(), textPanel = new JPanel(), csvPanel = new JPanel();

    // layout
    java.awt.CardLayout cardLayout = new java.awt.CardLayout();

    public FileUI() {
        cardPanel.setLayout(cardLayout);
        cardPanel.setBackground(Color.red);
        csvPanel.add(tableComponent);

        cardPanel.add(textPanel, "1");
        cardPanel.add(csvPanel, "2");

        textPanel.add(textAreaComponent);

        add(treeComponent, BorderLayout.WEST);
        add(cardPanel);

        readFileFromTreeDirectory();
    }

    public void readFileFromTreeDirectory() {
        tree = treeComponent.tree;
        tree.addTreeSelectionListener(e ->{
            DefaultMutableTreeNode selectedFile = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            String fileExtension = getFileExtension(selectedFile);

            if (Objects.equals(fileExtension, "txt")){
                textAreaComponent.readFileFromTreeDirectory(tree);
                cardLayout.show(cardPanel, "1");

            } else {
                tableComponent.setTable(new File(String.valueOf(selectedFile)));
                cardLayout.show(cardPanel, "2");
            }
        });
    }

    public Container getPanel() {
        return this.getContentPane();
    }

    public String getFileExtension(DefaultMutableTreeNode selectedFile){
        String file = String.valueOf(selectedFile);
        return FilenameUtils.getExtension(file);
    }

    public static void main(String[] args) {
        FileUI frame = new FileUI();
        frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
