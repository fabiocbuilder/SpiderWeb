/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Creates the south panel of the application. South panel contains various
 * dynamic info of the process, such as the current number of total contacts or
 * email.
 *
 * @author Fabio
 */
public class SouthPanel extends JPanel {

    /**
     * Represents a bordered panel which shows the current number of contacts
     */
    private final JPanel contactPanel;
    /**
     * Represents a bordered panel which shows the current number of email
     */
    private final JPanel mailPanel;
    /**
     * Represents a bordered panel which shows the statusPanel of the process
     */
    private final JPanel statusPanel;

    private final JLabel statusLabel;
    private final JLabel contactLabel;
    private final JLabel emailLabel;

    /**
     * Represents the variable which contains the number of contacts
     */
    private int contactCounter;

    /**
     * Represents the variable which contains the number of email 
     */
    private int emailCounter;

    /**
     * Creates and initializes the south panel
     */
    public SouthPanel() {
        super();
        this.setLayout(new GridLayout(1, 3));
        contactCounter = 0;
        contactLabel = new JLabel("Contatti totali: " + contactCounter);
        contactPanel = new JPanel();
        contactPanel.setBorder(new EtchedBorder(20));
        contactPanel.add(contactLabel);
        emailCounter = 0;
        emailLabel = new JLabel("Email totali: " + contactCounter);
        mailPanel = new JPanel();
        mailPanel.setBorder(new EtchedBorder(20));
        mailPanel.add(emailLabel);
        statusLabel = new JLabel("Stato: " + "0%");
        statusPanel = new JPanel();
        statusPanel.setBorder(new EtchedBorder(20));
        statusPanel.add(statusLabel);
        this.add(contactPanel);
        this.add(mailPanel);
        this.add(statusPanel);
    }

    public JLabel getContactLabel() {
        return contactLabel;
    }

    public void setContactLabel(String contactLabel) {
        this.contactLabel.setText(contactLabel);
    }

    public int getContactCounter() {
        return contactCounter;
    }

    public void setContactCounter(int contactCounter) {
        this.contactCounter = contactCounter;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(String emailLabel) {
        this.emailLabel.setText(emailLabel);
    }

    public int getEmailCounter() {
        return emailCounter;
    }

    public void setEmailCounter(int emailCounter) {
        this.emailCounter = emailCounter;
    }
    
}
