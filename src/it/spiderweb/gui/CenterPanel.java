/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import it.spiderweb.gui.customtable.CustomTableOP;
import it.spiderweb.gui.customtable.Element;
import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JPanel;

/**
 * Creates the center panel of the application.
 * The center panel is composed by table which contains all the data taken
 * from the web address.
 * Users can view dynamic data from the table 
 * @author Fabio
 */
public class CenterPanel extends JPanel {
    
    /**
     * Represents the table which contains all of the data from the web page
     */
    public static CustomTableOP<Element> TABLE;

    /**
     * Creates and initializes the center panel
     */
    public CenterPanel(){
        super();  
        this.setLayout(new BorderLayout());
        try {
            TABLE = new CustomTableOP<>(new Element());  
            this.add(TABLE,"Center");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
