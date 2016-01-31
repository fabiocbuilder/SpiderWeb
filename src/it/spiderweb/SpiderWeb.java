/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb;

import it.sauronsoftware.grab4j.ScriptException;
import it.sauronsoftware.grab4j.html.HTMLParseException;
import it.spiderweb.bl.Spider;
import it.spiderweb.gui.MainFrame;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Fabio
 */
public class SpiderWeb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.paginebianche.it/ricerca?qs=elettricista&dv=bologna");
//            URL url = new URL("http://www.paginegialle.it/");
            File criteria = new File("src/blankpages-grabbing.js");
            Spider sp = new Spider(url,criteria);
            System.out.println(sp.getJsonArray());  
            System.out.print("story agaaaaaaaain");
            } catch (MalformedURLException ex) {
            Logger.getLogger(SpiderWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | HTMLParseException | ScriptException ex) {
            System.out.println(ex);
        }
        
    }

}
