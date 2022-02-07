package com.letscode.brazilian_soccer_championship.ui.input;

import com.letscode.brazilian_soccer_championship.ui.shared.CustomIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class InputFile extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel messageLabel;
    private JFileChooser fileChooserCSV;

    private File csvFile;

    public InputFile() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.setResizable(false);
    }

    public File getCsvFile() {
        return csvFile;
    }

    private void onOK() {
        fileChooserCSV = new JFileChooser("./src/main/resources");
        fileChooserCSV.setFileFilter(new FileNameExtensionFilter("Apenas .csv file", "csv"));
        int choice = fileChooserCSV.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION){
            csvFile = fileChooserCSV.getSelectedFile();
            dispose();
        }else{
            System.out.println("Cancelou");
        }
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        contentPane = new MainPanel();

        ImageIcon logIcon = CustomIcon.buildLogoIcon(50, 50);
        messageLabel = new JLabel(
                    "<html>" +
                        "<h1 align=\"center\" color=\"green\">Bem vindo!</h1>" +
                        "<br>" +
                        "<p align=\"center\" color=\"#EEE\">Escolha um arquivo csv para iniciar.</p>" +
                        "</html>",
                logIcon, SwingConstants.RIGHT);
    }

    public void setLocation(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = this.getSize().width;
        int h = this.getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        this.setLocation(x, y);
    }
}


class MainPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Image img = ImageIO.read(new File("./src/main/resources/images/background.jpg"));
            g.drawImage(img, 0, 0, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
