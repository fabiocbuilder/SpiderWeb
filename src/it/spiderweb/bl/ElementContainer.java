/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.bl;

import java.util.List;

/**
 *
 * @author agrimandi
 */
public class ElementContainer {

    public List<Element> elements;
    
    public ElementContainer(){}
    
    public ElementContainer(List<Element> elements){
        this.elements = elements;
    }
    
    public List<Element> getElements(){
        return elements;
    }
    
    @Override
    public String toString(){
        String output ="";
        for(Element elem : elements){
            output += elem.toString() +"\n\n";
        }
        return output;
    }
}
