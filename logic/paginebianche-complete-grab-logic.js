/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
importClass(Packages.it.spiderweb.bl.SearchHandler);

var doc = document;

var rgs = null;
var address = null;
var cap = null;
var district = null;
var territory = null;
var tel = null;
var fax = null;
var website = null;
var email = null;

/**
 * Contiene il numero della pagina corrente
 * @type Number
 */
var current_page = 1;

/**
 * Contiene il numero delle pagine totati
 * @type Number
 */
var tot_page = null;

/**
 * Cerca l'indicatore delle pagine totali per questa ricerca e ne estrae il contenuto.
 * Memorizza poi il contenuto dentro la variabile delle pagine totali
 * Se la pagina non contiene pagine successive, ritorna 1
 * @see tot_page;
 */
function getTotPage(){
    tot_page = document.searchElement("html/body/.../div(class=pag-group)/.../p(class=listing-pag-r)/b[1]"); //vado a pescarmi il numerino che indica il numero totale di pagine 
    if (tot_page !== null) {
        tot_page = tot_page.getInnerText();
    }
    else {
        tot_page = 1;
    }
}

/**
 * Contiene il riferimento all'url principale
 * @type @exp;doc@pro;URL
 */
var doc_url = doc.URL;

/**
 * Il nostro caro JSON
 * @type String
 */
var json = "[";

/**
 * Variabile che contiene il riferimento a un oggetto di tipo ElementHandler
 * Questo oggetto serve per aggiornare dinamicamente gli elementi grafici, durante
 * la rircerca dei dati
 * @type ElementHandler
 * @see search();
 */
var handler = new SearchHandler();

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
            case district:
                var text = tag.getInnerText();
                output = text.substring(0, text.indexOf("("));
                break;
            case territory:
                var text = tag.getInnerText();
                output = text.substring(text.indexOf("(") + 1, text.indexOf(")"));
                break;
            default:
                output = tag.getInnerText();
        }
    }
    return output;
}

/*
 * Mette in pausa il thread in esecuzione secondo il parametro specificato (in millisecondi)
 * Riferimento: http://stackoverflow.com/questions/16623852/how-to-pause-javascript-code-excution-for-2-seconds
 * @param {type} miliseconds
 * @returns {undefined}
 */
function sleep(miliseconds) {
   var currentTime = new Date().getTime();
   while (currentTime + miliseconds >= new Date().getTime()) {}
}

/*
 * Naviga la pagina principale di riferimento. 
 * Entra dentro ai link delle singole aziende, prende le informazioni, esce e
 * rientra nella successiva, fino al termine della lista.
 * Dopodichè guarda se ci sono eventuali pagine successive, se si, prosegue fino 
 * a terminarle, altrimento si ferma.
 * Inserisce ogni informazione in un json strutturato.
 * Ritorna il json finito
 * @returns {json|String}
 */
function search(){
    handler.update();
    getTotPage();
    do {
        doc = openDocument(doc_url + "&p=" + current_page++);
        var list = doc.searchElements(".../div(class=list-left)/div(id=co_*)");
        for (var i = 0; i < list.length; i++) {
            var pageLink = list[i].searchElement(".../h2(class=rgs)/a");
            /* Controllo pericoloso da ricordare; Scarta le pagine nulle o che reindirizzano a siti propri. 
             * Infatti tutte le pagine analizzate in questo modulo riguardano le specifiche di una azienda 
             * dentro al sito "www.paginebianche.it" e sono nella forma url: /nome_azienda-comune */
            if (pageLink !== null && pageLink.getAttribute("href").indexOf("http") < 0) {
                doc = openDocument(pageLink.getLinkURL()); 
                var div_indirizzo = ".../div(class=indirizzo)/";
                rgs = doc.searchElement(div_indirizzo + "/h1");
                address = doc.searchElement(div_indirizzo + ".../span(class=street-address)");
                cap = doc.searchElement(div_indirizzo + ".../span(itemprop=postalCode)"); 
                district = doc.searchElement(div_indirizzo + ".../span(class=locality)"); //locality è un tag che contiene una stringa la quale ingloba provincia e comune, che bisogna separare @see format(tag)
                territory = doc.searchElement(div_indirizzo + ".../span(class=locality)");
                tel = doc.searchElement(div_indirizzo + ".../span(class=tel)");
                fax = null;
                website = null;
                email = doc.searchElement(div_indirizzo + ".../span(class=e-mail)/a");
                json = "{\"rgs\":" + "\"" + format(rgs) + "\"" + "," +
                        "\"address\":" + "\"" + format(address) + "\"" + "," +
                        "\"district\":" + "\"" + format(district) + "\"" + "," +
                        "\"territory\":" + "\"" + format(territory) + "\"" + "," +
                        "\"cap\":" + "\"" + format(cap) + "\"" + "," +
                        "\"tel\":" + "\"" + format(tel) + "\"" + "," +
                        "\"fax\":" + "\"" + format(fax) + "\"" + "," +
                        "\"website\":" + "\"" + format(website) + "\"" + "," +
                        "\"email\":" + "\"" + format(email) + "\"" + "}" + ",";
            }
            handler.update(json.substring(0,json.length -1));
            sleep(2000);
        }
        sleep(8000);
    } while(current_page <= tot_page);
    return "";
}
result = search();