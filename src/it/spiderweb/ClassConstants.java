/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb;

import it.spiderweb.bl.ElementContainer;
import it.spiderweb.gui.customtable.CustomTableOP;
import it.spiderweb.gui.customtable.Element;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JLabel;

/**
 *
 * @author Fabio
 */
public class ClassConstants {
    
    private static ClassConstants classConstants = null;
    
    public static ElementContainer ELEMENT_CONTAINER = new ElementContainer();
    public static CustomTableOP<Element> TABLE;
    
    public static JLabel CONTACT_LABEL;
    public static JLabel MAIL_LABEL;
    
    /**
     * Represents the variable which contains the number of contacts
     */
    public static int TOT_CONTACTS;
    
    /**
     * Represents the variable which contains the number of email
     */
    public static int TOT_MAIL;
    
    protected ClassConstants(){
        ELEMENT_CONTAINER = new ElementContainer();
        TOT_CONTACTS = 0;
        TOT_MAIL = 0;
        CONTACT_LABEL = new JLabel("Contatti totali: " + TOT_CONTACTS);
        MAIL_LABEL = new JLabel("Mail totali: " + TOT_MAIL);
        try {
            TABLE = new CustomTableOP<>(new Element());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    public static final ClassConstants buildClassConstants(){
        if(classConstants == null) {
            classConstants = new ClassConstants();
        }
        return classConstants;
    }
    
    public static final void destroyClassConstants(){
        classConstants = null;
    }
}
