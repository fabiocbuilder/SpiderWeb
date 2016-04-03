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
 *
 * @author Fabio
 */
public class MainFrame extends JFrame {

    private NorthPanel north;
    private SouthPanel south;
    private CenterPanel center;

    /**
     * Initializes the main frame
     */
    public MainFrame() {
        /* Inizializzazione del main frame */
        super("Spider Web - v0.99 ");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.setJMenuBar(new Menu());
        Container contentPane = this.getContentPane();
        north = new NorthPanel();
        contentPane.add(north, "North");
        center = new CenterPanel();
        contentPane.add(center, "Center");
        south = new SouthPanel();
        contentPane.add(south, "South");
        this.setVisible(true);
    }

    public NorthPanel getNorth() {
        return north;
    }

    public void setNorth(NorthPanel north) {
        this.north = north;
    }

    public SouthPanel getSouth() {
        return south;
    }

    public void setSouth(SouthPanel south) {
        this.south = south;
    }

    public CenterPanel getCenter() {
        return center;
    }

    public void setCenter(CenterPanel center) {
        this.center = center;
    }
}
