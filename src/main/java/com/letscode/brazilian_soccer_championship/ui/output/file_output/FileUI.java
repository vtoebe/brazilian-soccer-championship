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
    TextAreaComponent textAreaComponent = new TextAreaComponent();
    static JTree tree;

    // panels
    JPanel cardPanel = new JPanel(), textPanel = new JPanel();
    JScrollPane csvPanel;

    // layout
    java.awt.CardLayout cardLayout = new java.awt.CardLayout();

    public FileUI() {
        cardPanel.setLayout(cardLayout);
        cardPanel.setBackground(Color.red);
        csvPanel = new JScrollPane(tableComponent);


        textPanel.add(textAreaComponent);

        cardPanel.add(textPanel, "1");
        cardPanel.add(csvPanel, "2");

        add(treeComponent, BorderLayout.WEST);
        add(cardPanel);

        tree = treeComponent.tree;

        readFileFromTreeDirectory();
    }

    public void readFileFromTreeDirectory() {
        tree.addTreeSelectionListener(e ->{
            DefaultMutableTreeNode selectedFile = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            String fileExtension = getFileExtension(selectedFile);

            System.out.println(fileExtension);

            if (Objects.equals(fileExtension, "txt")){
                textAreaComponent.readFile(String.valueOf(selectedFile));
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
