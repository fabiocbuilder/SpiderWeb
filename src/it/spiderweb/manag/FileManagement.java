/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.manag;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * This class lets you operate on a file
 * @author Fabio
 */
public class FileManagement {
    
    private final FileWriter file;
    private final PrintStream stream; 
    
    public FileManagement(String pathname) throws FileNotFoundException, IOException{
        file = new FileWriter(pathname,true);
        stream = new PrintStream(pathname);
    }
    
    public void writesAsCSV(String text){
        StringTokenizer token = new StringTokenizer(text,"\n");
        while(token.hasMoreTokens()){
            stream.print(token.nextToken()+",");
        }
        stream.println(); //a capo importantissimo per la creazione di "righe"
    }    
}
