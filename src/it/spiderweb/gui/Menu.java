/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import it.spiderweb.manag.ExportManagement;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Creates the main menu of the application
 *
 * @author Fabio
 */
public class Menu extends JMenuBar {

    /**
     * Represents the menu "File"
     */
    private final JMenu menuFile;

    /**
     * Represents the menu "Export" inside menu "File"
     */
    private final JMenu export;
    
    /**
     * Menu Item for the conversion to CSV file
     */
    private final JMenuItem csvExport;
    /**
     * Represents the menu item "Exit" inside menu "File"
     */
    private final JMenuItem exit;

    /**
     * Creates and initializes the main menu
     */
    public Menu() {
        super();
        menuFile = new JMenu("File");
        export = new JMenu("Export...");
        exit = new JMenuItem("Exit");
        csvExport = new JMenuItem("Export to CSV");
        csvExport.addActionListener(new ExportManagement("ExportedList.csv"));
        export.add(csvExport);
        menuFile.add(export);
        menuFile.add(exit);
        this.add(menuFile);
    }
}
