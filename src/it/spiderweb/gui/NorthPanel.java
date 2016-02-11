/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import it.sauronsoftware.grab4j.ScriptException;
import it.sauronsoftware.grab4j.html.HTMLParseException;
import it.spiderweb.bl.Spider;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        search.addActionListener(new SearchManagement());
        this.setLayout(new BorderLayout()); //Orientamento degli elementi da sinistra a destra
        this.setBorder(new EtchedBorder(10));
        this.add(address, "West");
        this.add(txtAddress, "Center");
        this.add(search, "East");
    } 
   
    class SearchManagement implements ActionListener {

        URL url;
        File criteria;
        
        public SearchManagement(){
            url = null;
            criteria = null;
        }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String txt_url = txtAddress.getText();
            url = new URL(txt_url);
            switch(url.getHost()){
                case "www.paginebianche.it": 
                    criteria = new File("src/it/spiderweb/paginebianche-grab-logic.js");
                    break;
                case "paginegialle":
                    criteria = new File("samalaffatti??");
                    break;              
            }
            Spider spider = new Spider(url,criteria);
            System.out.println(spider.getJsonArray()); 
            } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException | HTMLParseException | ScriptException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
}
