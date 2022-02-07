package com.letscode.brazilian_soccer_championship.ui.output.file_output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableComponent extends JPanel {
    File inputFile;
    Vector<Vector<String>> vectorVectorStringsData = new Vector<>();
    Vector<String> vectorColumnIdentifiers = new Vector<String>();
    JTable jTable = new JTable();

    public TableComponent() {
        add(jTable);
    }

    public void setTable(File file){
        inputFile = file;
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);

        getFileLinesData();
        model.setColumnIdentifiers(vectorColumnIdentifiers);
        model.setDataVector(vectorVectorStringsData, vectorColumnIdentifiers);

        jTable.setModel(model);
    }

    public void getFileLinesData() {
        String[] columnIdentifiers;
        String firstRow;
        Vector<String> vectorStrings;
        try (FileReader fr = new FileReader(inputFile);
             BufferedReader br = new BufferedReader(fr))
        {
            firstRow = br.readLine().trim();
            // headers:
            columnIdentifiers = firstRow.split(";");

            vectorColumnIdentifiers = new Vector<String>(Arrays.asList(columnIdentifiers));
            // rows
            Object[] tableLines = br.lines().toArray();
            // data rows
            for (int i = 0; i < tableLines.length; i++) {
                String line;
                line = tableLines[i].toString().trim();

                String[] dataRow = line.split(";");
                vectorStrings = new Vector<String>(Arrays.asList(dataRow));
                vectorVectorStringsData.add(vectorStrings);

            }
        }
        catch (IOException ioe) {
            System.out.println("error: " + ioe.getMessage());
        }
    }

    public static void main(String[] args) {
        TableComponent frame = new TableComponent();
        frame.setVisible(true);
    }
}
