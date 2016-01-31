/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ret = "";
var doc = document;

var json = "{\"users-info\":[";

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
    var text; //variabile di appoggio per modifiche alla vladimir
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
                /* cap ha un passaggio di più perchè non l'hanno messo dentro un attributo */
            case cap:
                text = (tag.getElement(1)).getInnerText(); //il cap non può essere preso direttamente da searchElement
                output = (cut(text, "-")).trim();
                break;
                /* il caso district nasce per suddividere l'elemento "span(class=locality)",
                 * che contiene comune e provincia in una unica stringa, in due variabili separate
                 * Modifica a territory -----> PORCATA DISHUMAN
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

var list = doc.searchElements(".../div(class=list-left)/div(id=co_*)");

for (var i = 0; i < list.length; i++) {
    var pageLink = list[i].searchElement(".../h2(class=rgs)/a");
    /* Controllo pericoloso da ricordare; Scarta le pagine nulle o che reindirizzano a siti propri. 
     * Infatti tutte le pagine analizzate in questo modulo riguardano le specifiche di una azienda 
     * dentro al sito "www.paginebianche.it" e sono nella forma url: /nome_azienda-comune */
    if (pageLink !== null && pageLink.getAttribute("href").indexOf("http") < 0) {
        doc = openDocument(pageLink.getLinkURL());
        var div_indirizzo = ".../div(class=indirizzo)/";
        var rgs = doc.searchElement(div_indirizzo + "/h1");
        var street_adrs = doc.searchElement(div_indirizzo + ".../span(class=street-address)");
        var cap = doc.searchElement(div_indirizzo + ".../div[0](class=address)");
        var district = doc.searchElement(div_indirizzo + ".../span(class=locality)");
        var territory = doc.searchElement(div_indirizzo + ".../span(class=locality)");
        var tel = doc.searchElement(div_indirizzo + ".../span(class=tel)");
        var fax = null;
        var website = null;
        var email = doc.searchElement(div_indirizzo + ".../span(class=e-mail)/a");
        json += "{\"rgs\":" + "\"" + format(rgs) + "\"" + "," +
                "\"street_adrs\":" + "\"" + format(street_adrs) + "\"" + "," +
                "\"district\":" + "\"" + format(district) + "\"" + "," +
                "\"territory\":" + "\"" + territory + "\"" + "," +
                "\"cap\":" + "\"" + format(cap) + "\"" + "," +
                "\"tel\":" + "\"" + format(tel) + "\"" + "," +
                "\"fax\":" + "\"" + format(fax) + "\"" + "," +
                "\"website\":" + "\"" + format(website) + "\"" + "," +
                "\"email\":" + "\"" + format(email) + "\"" + "}" + ",";
    }
}

json = json.slice(0, json.length - 1) + "]}"; //cava l'ultima virgola (di eccesso, posta dopo l'ultima parentesi graffa) prima di chiudere il json

result = json;