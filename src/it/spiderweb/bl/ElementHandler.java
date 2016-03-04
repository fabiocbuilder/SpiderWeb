/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.bl;

import com.google.gson.Gson;
import it.spiderweb.ClassConstants;
import it.spiderweb.gui.customtable.Element;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabio
 */
public class ElementHandler {

    /**
     * Updates the GUI elements
     * @param json
     */
    public static void update(String json) {
        Gson gson = new Gson();
        try {
            Element element = gson.fromJson(json, Element.class);
            ClassConstants.ELEMENT_CONTAINER.add(element);
            ClassConstants.TABLE.addElement(element);
            ClassConstants.TOT_CONTACTS = ClassConstants.ELEMENT_CONTAINER.size();
            if (!element.getEmail().equals("")) {
                ClassConstants.TOT_MAIL++;
            }
            ClassConstants.TABLE.repaint();
            ClassConstants.CONTACT_LABEL.setText("Contatti totali: " + ClassConstants.TOT_CONTACTS);
            ClassConstants.CONTACT_LABEL.repaint();
            ClassConstants.MAIL_LABEL.setText("Mail totali: " + ClassConstants.TOT_MAIL);
            ClassConstants.MAIL_LABEL.repaint();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Table Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void reset(){
        ClassConstants.destroyClassConstants();
        ClassConstants.buildClassConstants();
        ClassConstants.TABLE.repaint();
        ClassConstants.CONTACT_LABEL.repaint();
        ClassConstants.MAIL_LABEL.repaint();
    }
    
}
