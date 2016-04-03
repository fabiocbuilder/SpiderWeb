/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.manag;

import it.spiderweb.SpiderWeb;
import it.spiderweb.gui.customtable.Element;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class ExportManagement implements ActionListener, Runnable {

    private final LinkedList<Element> elementContainer;
    private FileManagement file;
    
    public ExportManagement(String pathName){
        elementContainer = SpiderWeb.elementContainer;
        try {
            file = new FileManagement(pathName);
        } catch (IOException ex) {
            Logger.getLogger(ExportManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(this,"ExportThread").start();
    }

    @Override
    public void run() {
        String output = "";
        for(Element elem : elementContainer){
            output = elem.getRgs() + ","
                    + elem.getAddress() + ","
                    + elem.getDistrict() + ","
                    + elem.getTerritory() + ","
                    + elem.getCap() + ","
                    + elem.getTel() + ","
                    + elem.getFax() +","
                    + elem.getWebsite() +","
                    + elem.getEmail();
            file.writesAsCSV(output);
        }
    }
    
}
