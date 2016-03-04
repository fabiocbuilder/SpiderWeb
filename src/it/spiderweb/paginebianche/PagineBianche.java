/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.paginebianche;

import it.sauronsoftware.grab4j.ScriptException;
import it.sauronsoftware.grab4j.WebGrabber;
import it.sauronsoftware.grab4j.html.HTMLDocument;
import it.sauronsoftware.grab4j.html.HTMLDocumentFactory;
import it.sauronsoftware.grab4j.html.HTMLElement;
import it.sauronsoftware.grab4j.html.HTMLLink;
import it.sauronsoftware.grab4j.html.HTMLParseException;
import it.spiderweb.bl.ElementHandler;
import it.spiderweb.bl.Spider;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabio
 */
public class PagineBianche {
    
    private URL url;  
    private final File[] criteria;
    
    public PagineBianche(){
        criteria = new File[] {
            new File("src/it/spiderweb/paginebianche/getpage.js"),
            new File("src/it/spiderweb/paginebianche/grab.js")
        };
    }
    
    public PagineBianche(URL url){
        this();
        this.url = url;  
    }
    
    public URL getUrl(){
        return this.url;
    }
    
    public void setUrl(URL url){
        this.url = url;
    }
    
    public void search(){
        String json;
        int current_page = 1;
        try {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
            connection.connect();
            HTMLDocument htmlDocument = HTMLDocumentFactory.buildDocument(connection.getURL()); //costruisce il documento passato
            int tot_page = Integer.valueOf(""+WebGrabber.grab(htmlDocument, criteria[0])); //ricava le pagine totali
            do {
                URL currentUrl = new URL(url.toString() + "&p=" + current_page++); //setta l'url in modo da scorrere le pagine
                htmlDocument.setURL(currentUrl);
                HTMLElement[] list = htmlDocument.searchElements(".../div(class=list-left)/div(id=co_*)"); //prende il bottino di elementi
                for (HTMLElement elem : list) {
                    HTMLLink page_link = (HTMLLink) elem.searchElement(".../h2(class=rgs)/a"); //prende i link che reindirizzano alla pagina specifica dell'azienda 
                    currentUrl = new URL(page_link.getLinkURL());
                    if (currentUrl.getHost().equals(url.getHost()) == true) { //non elabora pagine che reindirizzano a siti propri o nulle
                        Spider spider = new Spider(currentUrl, criteria[1]);
                        json = spider.getJsonArray();
                        ElementHandler.update(json.substring(0, json.length()-1)); //update la lista di elementi e la table in modo BRUTAL
                    }
                }
                Thread.sleep(8000);
            } while (current_page <= tot_page);
        } catch (IOException | ScriptException | HTMLParseException ex) {
            switch(ex.getMessage()){
                case "www.paginebianche.it": search();
                    break;
                default: JOptionPane.showMessageDialog(null, ex.getMessage(), "Grab Error", JOptionPane.ERROR_MESSAGE);
            }           
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Interrupted Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void search(URL url, File criteria){
        try {
            Spider spider = new Spider(url, criteria);
            String json = spider.getJsonArray();
            ElementHandler.update(json);
        } catch (IOException | ScriptException | HTMLParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), " Grab Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
