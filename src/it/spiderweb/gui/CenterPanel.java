/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import it.spiderweb.ClassConstants;
import it.spiderweb.gui.customtable.CustomTableOP;
import it.spiderweb.gui.customtable.Element;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Creates the center panel of the application. The center panel is composed by
 * table which contains all the data taken from the web address. Users can view
 * dynamic data from the table
 *
 * @author Fabio
 */
public class CenterPanel extends JPanel {

    /**
     * Represents the table which contains all of the data from the web page
     */
    private final CustomTableOP<Element> table;

    /**
     * Creates and initializes the center panel
     */
    public CenterPanel() {
        super();
        this.setLayout(new BorderLayout());
        table = ClassConstants.TABLE;
        this.add(table, "Center");
    }
}
