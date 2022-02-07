package com.letscode.brazilian_soccer_championship.ui.output.file_output;

import com.letscode.brazilian_soccer_championship.services.FileService;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeComponent extends JPanel {
    File projectFile = new File("./src/main/resources");

    public JTree tree;

    public TreeComponent() {
        setLayout(new BorderLayout());
        tree = new JTree(addNodes(projectFile));
        tree.setCellRenderer(new MyTreeCellRenderer());
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);

        JScrollPane treeScrollPane = new JScrollPane();
        treeScrollPane.setViewportView(tree);
        add(treeScrollPane, BorderLayout.CENTER);
    }

    public static DefaultMutableTreeNode addNodes(File dir) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(dir);

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) { node.add(addNodes(file)); }
            else { node.add(new DefaultMutableTreeNode(file)); }
        }
        return node;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.add(new TreeComponent());
        frame.setMaximumSize(new Dimension(30, -1));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MyTreeCellRenderer extends DefaultTreeCellRenderer {
    FileSystemView fsv = FileSystemView.getFileSystemView();
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        renderTreeComponents(value);
        return this;
    }

    private void renderTreeComponents(Object value) {
        if (value instanceof DefaultMutableTreeNode) {
            value = ((DefaultMutableTreeNode) value).getUserObject();
            if (value instanceof File) {
                String name = ((File) value).getName();
                File file = (File) value;

                setIcon(fsv.getSystemIcon(file));
                setText(name);
            }
        }
    }
}
