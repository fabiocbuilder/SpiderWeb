/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.manag;

import it.sauronsoftware.grab4j.ScriptException;
import it.sauronsoftware.grab4j.html.HTMLParseException;
import it.spiderweb.gui.NorthPanel;
import it.spiderweb.bl.Spider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabio
 */
public class SearchManagement implements ActionListener, Runnable {

    private final NorthPanel north;

    public SearchManagement(NorthPanel north) {
        this.north = north;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        north.setSearchEnabled(false);
        new Thread(this, "SearchingThread").start();
    }

    @Override
    public void run() {
        try {
            Spider spider;
            URL url;
            File criteria;
            url = new URL(north.getTextAddress().getText());
            switch (url.getHost()) {
                case "www.paginebianche.it":
                    criteria = new File("logic/paginebianche-complete-grab-logic.js");
                    break;
                case "www.paginegialle.it":
                    criteria = new File("logic/paginegialle-complete-grab-logic.js");
                    break;
                default:
                    criteria = new File("");
                    break;
            }
            spider = new Spider(url, criteria);
            spider.getJsonArray();
            JOptionPane.showMessageDialog(null, "Congratulations, you have succesfully completed yout research", "Research Completed!", JOptionPane.WARNING_MESSAGE);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "URL Error", JOptionPane.ERROR_MESSAGE);
        } catch (HTMLParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Parse Error", JOptionPane.ERROR_MESSAGE);
        } catch (ScriptException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Script Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            switch (ex.getMessage()) {
                case "":
                    JOptionPane.showMessageDialog(null, "No logic has been implemented for this domain", "IOError", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "IOError", JOptionPane.ERROR_MESSAGE);
            }
        }
        north.setSearchEnabled(true);
    }
}
