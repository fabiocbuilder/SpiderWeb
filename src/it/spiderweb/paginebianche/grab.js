/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var rgs = null;
var address = null;
var cap = null;
var district = null;
var territory = null;
var tel = null;
var fax = null;
var website = null;
var email = null;

var json = "";

/**
 * Questa funzione toglie inutili informazioni aggiuntive di una specifica 
 * stringa di testo, a partire da uno specifico elemento.
 * @param {type} text (stringa di testo)
 * @param {type} elem (elemento di riferiemento)
 * @return {type} text (lo stesso testo, modificato)
 */
function cut(text, elem) {
    var index = text.indexOf(elem);
    if (index > 0) {
        text = text.substring(index + 2);
    }
    return text;
}

/**
 * Questa funzione restituisce il testo della variabile passata come parametro
 * formattato secondo i critieri stabiliti.
 * @param {type} tag
 * @returns {unresolved|String} output
 */
function format(tag) {
    var text; //variabile di appoggio per modifiche 
    var output;
    if (tag === null) {
        output = "";
    }
    else {
        switch (tag) {
            case tel:
            case fax:
                output = cut(tag.getInnerText(), ':');
                break;
                /* il caso district nasce per suddividere l'elemento "span(class=locality)",
                 * che contiene comune e provincia in una unica stringa, in due variabili separate.
                 */
            case district:
                text = tag.getInnerText();
                output = text.substring(0, text.indexOf("("));
                territory = text.substring(text.indexOf("(") + 1, text.indexOf(")"));
                break;
            default:
                output = tag.getInnerText();
        }
    }
    return output;
}

/*
 * Mette in pausa il thread in esecuzione secondo il parametro specificato (in millisecondi)
 * Grande stima per: http://stackoverflow.com/questions/16623852/how-to-pause-javascript-code-excution-for-2-seconds
 * @param {type} miliseconds
 * @returns {undefined}
 */
function sleep(miliseconds) {
   var currentTime = new Date().getTime();
   while (currentTime + miliseconds >= new Date().getTime()) {}
}

/*
 * Naviga la pagina principale di riferimento. 
 * Entra dentro ai link dell'azienda, prende le informazioni,
 * inserisce ogni informazione in un json strutturato.
 * Ritorna il json finito
 * @returns {json|String}
 */
function search(){
    var div_indirizzo = ".../div(class=indirizzo)/";
    rgs = document.searchElement(div_indirizzo + "/h1");
    address = document.searchElement(div_indirizzo + ".../span(class=street-address)");
    cap = document.searchElement(div_indirizzo + ".../span(itemprop=postalCode)"); 
    district = document.searchElement(div_indirizzo + ".../span(class=locality)");
    territory = document.searchElement(div_indirizzo + ".../span(class=locality)");
    tel = document.searchElement(div_indirizzo + ".../span(class=tel)");
    fax = null;
    website = null;
    email = document.searchElement(div_indirizzo + ".../span(class=e-mail)/a");
    json += "{\"rgs\":" + "\"" + format(rgs) + "\"" + "," +
            "\"address\":" + "\"" + format(address) + "\"" + "," +
            "\"district\":" + "\"" + format(district) + "\"" + "," +
            "\"territory\":" + "\"" + territory + "\"" + "," +
            "\"cap\":" + "\"" + format(cap) + "\"" + "," +
            "\"tel\":" + "\"" + format(tel) + "\"" + "," +
            "\"fax\":" + "\"" + format(fax) + "\"" + "," +
            "\"website\":" + "\"" + format(website) + "\"" + "," +
            "\"email\":" + "\"" + format(email) + "\"" + "}" + ",";
    var time = ((Math.random()+2)*1000);
    sleep(time);
    return json;
}

result = search();