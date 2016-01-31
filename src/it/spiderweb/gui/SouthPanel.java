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
     * Represents the variable which contains the number of contacts
     */
    private final int tot;
    /**
     * Represents the variable which contains the number of email
     */
    private final int totMail;

    /**
     * Creates and initializes the south panel
     */
    public SouthPanel() {
        super();
        this.setLayout(new GridLayout(1, 3));
        tot = 0;
        totMail = 0;
        allRis = new JPanel();
        allRis.setBorder(new EtchedBorder(20));
        allRis.add(new JLabel("Contatti totali: " + tot));
        mailRis = new JPanel();
        mailRis.setBorder(new EtchedBorder(20));
        mailRis.add(new JLabel("Mail totali: " + totMail));
        status = new JPanel();
        status.setBorder(new EtchedBorder(20));
        status.add(new JLabel("Stato: " + "0%")); //stringa della percentuale brutale
        this.add(allRis);
        this.add(mailRis);
        this.add(status);
    }
}
