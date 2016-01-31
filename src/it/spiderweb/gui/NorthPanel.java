/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * Creates the north panel of the application The north panel is mainly composed
 * by the search bar where users can write or paste one web address and starting
 * the research of data
 *
 * @author Fabio
 */
public class NorthPanel extends JPanel {

    /**
     * Represents the label "Address: "
     */
    private final JLabel address;
    /**
     * Represents the text filed where users can write or paste web addresses
     */
    private final JTextField txtAddress;
    /**
     * Represents the "Search" button
     */
    private final JButton search;

    /**
     * Creates and initializes a newly north panel
     */
    public NorthPanel() {
        super();
        this.setName("North Panel");
        address = new JLabel("Address: ");
        txtAddress = new JTextField();
        search = new JButton("Search");
        this.setLayout(new BorderLayout()); //Orientamento degli elementi da sinistra a destra
        this.setBorder(new EtchedBorder(10));
        this.add(address, "West");
        this.add(txtAddress, "Center");
        this.add(search, "East");
    }

}
