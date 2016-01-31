/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menuFile, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * Creates the main frame of the application
 * @author Fabio
 */
public class MainFrame extends JFrame {

    /**
     * Initializes the main frame 
     */
    public MainFrame(){
        /* Inizializzazione del main frame */
        super("Crawler Web - v0.99 ");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 500);  
        this.setJMenuBar(new Menu());
        Container contentPane = this.getContentPane();
        contentPane.add(new NorthPanel(),"North");
        contentPane.add(new CenterPanel(),"Center");
        contentPane.add(new SouthPanel(),"South");
        this.setVisible(true);
    }
}
