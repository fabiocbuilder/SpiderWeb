/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.test;

import it.sauronsoftware.grab4j.ScriptException;
import it.sauronsoftware.grab4j.html.HTMLParseException;
import it.spiderweb.bl.Spider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author agrimandi
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.paginebianche.it/ricerca?qs=elettricista&dv=bologna");
//            URL url = new URL("http://www.paginegialle.it/");
            File criteria = new File("src/it/spiderweb/blankpages-grabbing.js");
            Spider sp = new Spider(url,criteria);
            System.out.println(sp.getJsonArray());  
            } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException | HTMLParseException | ScriptException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
