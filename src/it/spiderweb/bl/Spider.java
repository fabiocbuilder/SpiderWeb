/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.bl;

import it.sauronsoftware.grab4j.ScriptException;
import it.sauronsoftware.grab4j.WebGrabber;
import it.sauronsoftware.grab4j.html.HTMLDocument;
import it.sauronsoftware.grab4j.html.HTMLDocumentFactory;
import it.sauronsoftware.grab4j.html.HTMLParseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Constructs a spider/crawler for web pages. A spider web can search and found
 * specified items on a given web page by using a criteria. A criteria is
 * represented by a Javascript file which contains logic sequences for grabbing
 * specific elements of web pages. For example a criteria could be a Javascript
 * file which returns the title of a generic web page. By specifying a web page
 * and a criteria, the spider will apply the content of the criteria on the web
 * page.
 *
 * @author agrimandi
 */
public class Spider {

    /**
     * File path pointing to the file to scan
     */
    protected String path = null;

    /**
     * File to scan
     */
    protected File file = null;

    /**
     * URL pointing to the web page to scan
     */
    protected URL url = null;

    /**
     * File containing the grabbing logic
     */
    protected File criteria = null;

    //--------------------------------------------------------------------------
    /**
     *
     */
    public Spider() {
    }

    /**
     * Creates a newly spider object with a given path and criteria
     *
     * @param path
     * @param criteria
     */
    public Spider(String path, File criteria) {
        this.path = path;
        this.criteria = criteria;
    }

    /**
     * Creates a newly spider object with a given file and criteria
     *
     * @param file
     * @param criteria
     */
    public Spider(File file, File criteria) {
        this.file = file;
        this.criteria = criteria;
    }

    /**
     *
     * @param url
     * @param criteria
     */
    public Spider(URL url, File criteria) {
        this.url = url;
        this.criteria = criteria;
    }

    //--------------------------------------------------------------------------
    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return
     */
    public URL getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     *
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     *
     * @return
     */
    public File getCriteria() {
        return criteria;
    }

    /**
     *
     * @param criteria
     */
    public void setCriteria(File criteria) {
        this.criteria = criteria;
    }

    //--------------------------------------------------------------------------
    /**
     * Search in the <b>file</b> with the given <b>blLogicFile</b>
     *
     * @param file
     * @param criteria
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws HTMLParseException
     */
    public String getJsonArray(File file, File criteria) throws FileNotFoundException, IOException, HTMLParseException, ScriptException {
        InputStream inputStream = new FileInputStream(file);
        HTMLDocument htmlDocumentToScan = HTMLDocumentFactory.buildDocument(inputStream);
        return getJsonArray(htmlDocumentToScan, criteria);
    }

    /**
     * Search in the file <b>path</b> with the given <b>blLogicFile</b>
     *
     * @param path
     * @param criteria
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws HTMLParseException
     */
    public String getJsonArray(String path, File criteria) throws FileNotFoundException, IOException, HTMLParseException, ScriptException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        HTMLDocument htmlDocumentToScan = HTMLDocumentFactory.buildDocument(inputStream);
        return getJsonArray(htmlDocumentToScan, criteria);
    }

    /**
     * Search in the <b>url</b> page with the given <b>blLogicFile</b>
     *
     * @param url
     * @param criteria
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws HTMLParseException
     */
    public String getJsonArray(URL url, File criteria) throws FileNotFoundException, IOException, HTMLParseException, ScriptException {
        HTMLDocument htmlDocumentToScan = HTMLDocumentFactory.buildDocument(url);
        return getJsonArray(htmlDocumentToScan, criteria);
    }

    //--------------------------------------------------------------------------
    /**
     *
     * @return @throws FileNotFoundException
     * @throws IOException
     * @throws HTMLParseException
     */
    public String getJsonArrayFromFile() throws FileNotFoundException, IOException, HTMLParseException, ScriptException {
        InputStream inputStream = new FileInputStream(this.file);
        HTMLDocument htmlDocumentToScan = HTMLDocumentFactory.buildDocument(inputStream);
        return getJsonArray(htmlDocumentToScan, this.criteria);
    }

    /**
     *
     * @return @throws FileNotFoundException
     * @throws IOException
     * @throws HTMLParseException
     */
    public String getJsonArrayFromPath() throws FileNotFoundException, IOException, HTMLParseException, ScriptException {
        File file = new File(this.path);
        InputStream inputStream = new FileInputStream(file);
        HTMLDocument htmlDocumentToScan = HTMLDocumentFactory.buildDocument(inputStream);
        return getJsonArray(htmlDocumentToScan, this.criteria);
    }

    /**
     *
     * @return @throws FileNotFoundException
     * @throws IOException
     * @throws HTMLParseException
     * @throws it.sauronsoftware.grab4j.ScriptException
     */
    public String getJsonArray() throws FileNotFoundException, IOException, HTMLParseException, ScriptException {
        HTMLDocument htmlDocument = HTMLDocumentFactory.buildDocument(url);
        return getJsonArray(htmlDocument, criteria);
    }

    //--------------------------------------------------------------------------
    /**
     * Get a json array from a given htmlDocument with a specified criteria
     *
     * @param htmlDocument
     * @param criteria
     * @return
     * @throws java.io.IOException
     * @throws it.sauronsoftware.grab4j.ScriptException
     */
    protected String getJsonArray(HTMLDocument htmlDocument, File criteria) throws IOException, ScriptException {
        return (String) WebGrabber.grab(htmlDocument, criteria);
    }

}
