/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.manag;

import it.spiderweb.bl.ElementContainer;
import it.spiderweb.gui.customtable.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class lets you operate on a file
 *
 * @author Fabio
 */
public class FileManagement {

    private final File file;
    private final PrintStream stream;

    public FileManagement(String pathname) throws FileNotFoundException, IOException {
        file = new File(pathname);
        stream = new PrintStream(pathname);
    }

    /**
     * This method lets you write on this file as a CSV (Comma Separated Value)
     *
     * @param elementContainer
     */
    public void writesAsCSV(ElementContainer elementContainer) {
        stream.println("Azienda,Indirizzo,Comune,Provincia,CAP,Telefono,Fax,Sito Web,Email,");
        for (Element elem : elementContainer) {
            stream.println(elem.getRgs() + ","
                    + elem.getAddress() + ","
                    + elem.getDistrict() + ","
                    + elem.getTerritory() + ","
                    + elem.getCap() + ","
                    + elem.getTel() + ","
                    + elem.getFax() + ","
                    + elem.getWebsite() + ","
                    + elem.getEmail() + ",");
        }
    }
    
    public void clear(){
        try {
            new FileWriter(file);
        } catch (IOException ex) {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
