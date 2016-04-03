/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb;

import it.spiderweb.bl.ElementContainer;
import it.spiderweb.gui.MainFrame;
/**
 *
 * @author Fabio
 */
public class SpiderWeb {

    public static MainFrame spiderWeb;
    public static ElementContainer elementContainer;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        elementContainer = new ElementContainer();
        spiderWeb = new MainFrame();        
    }

}
