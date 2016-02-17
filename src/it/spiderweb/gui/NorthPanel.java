/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.gui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.sauronsoftware.grab4j.ScriptException;
import it.sauronsoftware.grab4j.html.HTMLParseException;
import it.spiderweb.bl.ElementContainer;
import it.spiderweb.bl.Spider;
import it.spiderweb.gui.customtable.CustomTableOP;
import it.spiderweb.gui.customtable.Element;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
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

        private URL url;
        private File criteria;
        private final Gson gson;
        private final ElementContainer tableElement;
        
        public SearchManagement() {
            url = null;
            criteria = null;
            gson = new Gson();
            tableElement = new ElementContainer();
        }
        
        private synchronized void toList(String json) {
            while(json.equals("")==true){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            Type collectionType = new TypeToken<Collection<Element>>(){}.getType();
            Collection<Element> list = gson.fromJson(json, collectionType);
            tableElement.addAll(list);
        }
        
        private void showTable(){
            CustomTableOP<Element> mainTable = CenterPanel.TABLE;
            try {
                mainTable.addElements(tableElement);
                mainTable.repaint();
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                System.out.println(ex.getMessage());
            }
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
                case "www.paginegialle.it":
                    criteria = new File("src/it/spiderweb/paginegialle-grab-logic.js");
                    break;              
            }
            
            Spider spider = new Spider(url,criteria);
            String json = spider.getJsonArray();
            
            toList(json);
            System.out.println(tableElement.toString());
            showTable();
            
            } catch (MalformedURLException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException | HTMLParseException | ScriptException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
