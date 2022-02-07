package com.letscode.brazilian_soccer_championship.ui.input;

import com.letscode.brazilian_soccer_championship.entities.Game;
import com.letscode.brazilian_soccer_championship.entities.Ranking;
import com.letscode.brazilian_soccer_championship.entities.Team;
import com.letscode.brazilian_soccer_championship.ui.output.MainOutputUI;

import javax.swing.*;
import java.io.File;
import java.util.Set;

public class InputUI {
    public static File inputCSV(){
        InputFile dialog = new InputFile();
        dialog.pack();
        dialog.setLocation();
        dialog.setVisible(true);
        return dialog.getCsvFile();
    }
}
