/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.manag;

import it.spiderweb.gui.NorthPanel;
import it.spiderweb.paginebianche.PagineBianche;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Fabio
 */
public class SearchManagement implements ActionListener, Runnable {

    private final NorthPanel north;
    private URL url;

    public SearchManagement(NorthPanel north) {
        this.north = north;
        url = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        north.setSearchInactive();
        new Thread(this, "SearchingThread").start();
    }
    
    @Override
    public void run() {
        try {
            url = new URL(north.getTextAdress());
            switch (url.getHost()) {
                case "www.paginebianche.it":
                    PagineBianche page = new PagineBianche(url);
                    page.search();
                    break;
                case "www.paginegialle.it":
                    //bellal vecchiooooo
                    break;
            }
            north.setSearchActive();
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
