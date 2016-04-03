/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.bl;

import com.google.gson.Gson;
import it.spiderweb.SpiderWeb;
import it.spiderweb.gui.CenterPanel;
import it.spiderweb.gui.NorthPanel;
import it.spiderweb.gui.SouthPanel;
import it.spiderweb.gui.customtable.Element;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabio
 */
public class SearchHandler extends ElementHandler {

    private final NorthPanel north;
    private final SouthPanel south;
    private final CenterPanel center;

    private final LinkedList<Element> elementContainer;

    public SearchHandler() {
        super();
        this.elementContainer = SpiderWeb.elementContainer;
        north = spiderWeb.getNorth();
        south = spiderWeb.getSouth();
        center = spiderWeb.getCenter();
    }

    @Override
    public void update(String json) {
        Gson gson = new Gson();
        int contactCounter = south.getContactCounter();
        int emailCounter = south.getEmailCounter();
        try {
            Element element = gson.fromJson(json, Element.class);
            elementContainer.add(element);
            center.addTableElement(element);
            south.setContactCounter(++contactCounter);
            if (!element.getEmail().equals("")) {
                south.setEmailCounter(++emailCounter);
            }
            south.setContactLabel("Contatti totali: " + contactCounter);
            south.setEmailLabel("Mail totali: " + emailCounter);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Gson Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update() {
        center.resetTable();
        south.setContactCounter(0);
        south.setEmailCounter(0);
        south.setContactLabel("Contatti Totali:" + 0);
        south.setEmailLabel("Email Totali:" + 0);
    }

}
