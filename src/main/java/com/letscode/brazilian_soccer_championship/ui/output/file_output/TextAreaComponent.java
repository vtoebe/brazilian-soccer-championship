package com.letscode.brazilian_soccer_championship.ui.output.file_output;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;

public class TextAreaComponent extends JPanel {
    JTextArea textArea;
    static TreeComponent directoryTreePane;
    static JTree tree;
    JScrollPane txtAreaScrollPane;

    public TextAreaComponent(JTree tree){
        TextAreaComponent.tree = tree;
        setTextArea();
        add(txtAreaScrollPane, BorderLayout.EAST);
    }

    private void setTextArea() {
        textArea = new JTextArea(40, 40);
        textArea.setMargin(new Insets(5,5,5,5));
        textArea.setEditable(false);
        txtAreaScrollPane = new JScrollPane(textArea);
        txtAreaScrollPane.setViewportView(textArea);
    }

    public void readFileFromTreeDirectory(JTree tree) {
        directoryTreePane = new TreeComponent();
        tree.addTreeSelectionListener(e ->{
            DefaultMutableTreeNode selectedFile = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(
                        new FileInputStream(String.valueOf(selectedFile))));
                textArea.read(input, "reading file");
            } catch (IOException ignored) { }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.add(new TextAreaComponent(tree));

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
