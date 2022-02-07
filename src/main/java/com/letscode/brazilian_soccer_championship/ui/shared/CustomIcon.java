package com.letscode.brazilian_soccer_championship.ui.shared;

import javax.swing.*;
import java.awt.*;

public class CustomIcon {
    private static final String SHIELDS_IMAGE_PATH = "src/main/resources/shields/";
    private static final String ICON_IMAGE_PATH = "src/main/resources/icons/";

    public static ImageIcon buildLogoIcon(int width, int height) {
        String filename = "logo.png";
        Image image = getImagesShield(ICON_IMAGE_PATH+filename, width, height);
        return new ImageIcon(image);
    }

    public static ImageIcon buildBallIcon(String ballColor, int width, int height) {
        String filename = ballColor + "_ball.png";
        Image image = getImagesShield(ICON_IMAGE_PATH+filename, width, height);
        return new ImageIcon(image);
    }

    public static ImageIcon buildTeamIcon(String teamName, int width, int height) {
        String filename = teamName
                .toLowerCase()
                .replace(" ", "-");
        filename += ".png";
        Image image = getImagesShield(SHIELDS_IMAGE_PATH+filename, width, height);

        if(image == null){
            System.out.println(teamName);
            image = getImagesShield(SHIELDS_IMAGE_PATH + "desconhecido.png", width, height);
        }
        return new ImageIcon(image);
    }

    private static Image getImagesShield(String path,  int width, int height) {
        ImageIcon icon = new ImageIcon(path);

        Image image = icon.getImage();

        if(icon.getIconWidth() == -1){
            return null;
        }
        return image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
    }
}