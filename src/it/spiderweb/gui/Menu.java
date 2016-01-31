/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Creates the main menu of the application
 * @author Fabio
 */
public class Menu extends JMenuBar {

    /**
     * Represents the menu "File"
     */
    private final JMenu menuFile;
    /**
     * Represents the voice "Exit" wrapped in the menu "File"
     */
    private final JMenuItem exit;

    /**
     * Creates and initializes the main menu 
     */
    public Menu() {
        super();
        menuFile = new JMenu("File");
        exit = new JMenuItem("Exit");
        menuFile.add(exit);
        this.add(menuFile);
    }
}
