/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.bl;

import it.spiderweb.gui.customtable.Element;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author agrimandi
 */
public class ElementContainer extends LinkedList<Element> {
    
    public ElementContainer(){super();}
    
    public ElementContainer(List<Element> elements){
        super(elements);
    }
    
    @Override
    public String toString(){
        String output = "";
        for(Element elem : this){
            output += elem.toString() +"\n\n";
        }
        return output;
    }
}
