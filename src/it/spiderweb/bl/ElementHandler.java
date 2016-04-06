package it.spiderweb.bl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import it.spiderweb.SpiderWeb;
import it.spiderweb.gui.MainFrame;

/**
 * Need help for the description of this class
 * @author Fabio
 */
public abstract class ElementHandler {

    protected final MainFrame spiderWeb;

    public ElementHandler(){
        this.spiderWeb = SpiderWeb.spiderWeb;
    }
       
    public abstract void update(String json);
}
