/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import it.spiderweb.ClassConstants;
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
    private final JPanel allRis;
    /**
     * Represents a bordered panel which shows the current number of email
     */
    private final JPanel mailRis;
    /**
     * Represents a bordered panel which shows the status of the process
     */
    private final JPanel status;

    /**
     * Creates and initializes the south panel
     */
    public SouthPanel() {
        super();
        this.setLayout(new GridLayout(1, 3));
        allRis = new JPanel();
        allRis.setBorder(new EtchedBorder(20));
        allRis.add(ClassConstants.CONTACT_LABEL);
        mailRis = new JPanel();
        mailRis.setBorder(new EtchedBorder(20));
        mailRis.add(ClassConstants.MAIL_LABEL);
        status = new JPanel();
        status.setBorder(new EtchedBorder(20));
        status.add(new JLabel("Stato: " + "0%")); //stringa della percentuale brutale
        this.add(allRis);
        this.add(mailRis);
        this.add(status);
    }
}
