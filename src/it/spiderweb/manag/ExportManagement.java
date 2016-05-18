/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.manag;

import it.spiderweb.SpiderWeb;
import it.spiderweb.bl.ElementContainer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class ExportManagement implements ActionListener {

    private final ElementContainer elementContainer;
    private FileManagement file;

    public ExportManagement(String pathName) {
        elementContainer = SpiderWeb.elementContainer;
        try {
            file = new FileManagement(pathName);
        } catch (IOException ex) {
            Logger.getLogger(ExportManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        file.clear();
        switch (e.getActionCommand()) {
            case "Export to CSV":
                file.writesAsCSV(elementContainer);
                break;
        }
    }
}
