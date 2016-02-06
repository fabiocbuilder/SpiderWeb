/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var doc = document;

//var doc_url = doc.URL; <------> bugged for Firefox (https://developer.mozilla.org/en-US/docs/Web/API/Document/location)

/* Che incualta.. 
 * E' possibile accedere a particolari parametri come ad esempio l'url della pagina in due modi:
 * o da document o da window. 
 * 
 * Es:
 * document.URL o dcument.location.href --> ritornano l'url in una stringa
 * window.location.href --> ritorna l'url in una stringa
 * 
 * Come scritto nel link, questi attributi non sono accessibili dentro a un contesto SENZA BROWSER (se si usa FIREFOX)
 * window invece si.. ma window funziona SOLO se il programma tratta una FINESTRA BROWSER APERTA, che non è il nostro caso
 */

var doc_url = "http://" + doc.URL;

print(doc_url);

var current_page = 1;

/**
 * Indica il numero di pagine totali
 */
var tot_page = doc.searchElement("html/body/.../div(class=pag-group)/.../p(class=listing-pag-r)/b[1]"); //vado a pescarmi il numerino che indica il numero totale di pagine 

/*
 * Aggiungo un controllo per il numero totale di pagine (potrebbero giustamente non esserci più pagine per una ricerca)
 */
    if (tot_page !== null) {
        tot_page = tot_page.getInnerText();
    }
    else {
        tot_page = 1;
    }
    
/**
 * Il nostro caro JSON
 * @type String|String
 */
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
                /* il caso district nasce per suddividere l'elemento "span(class=locality)",
                 * che contiene comune e provincia in una unica stringa, in due variabili separate.
                 * >>>>>> Modifica a territory -----> PORCATA DISHUMAN <<<<<<<<
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

do {
    doc = openDocument(doc_url + "&p=" + (current_page++));
    var list = doc.searchElements(".../div(class=list-left)/div(id=co_*)");
    for (var i = 0; i < list.length; i++) {
        var pageLink = list[i].searchElement(".../h2(class=rgs)/a");
        /* Controllo pericoloso da ricordare; Scarta le pagine nulle o che reindirizzano a siti propri. 
         * Infatti tutte le pagine analizzate in questo modulo riguardano le specifiche di una azienda 
         * dentro al sito "www.paginebianche.it" e sono nella forma url: /nome_azienda-comune */
        if (pageLink !== null && pageLink.getAttribute("href").indexOf("http") < 0) {
            doc = openDocument("http://www.paginebianche.it" + pageLink.getLinkURL()); //cambio forse avvenuto per la setRequestProperty()
            print(doc.URL);
            var div_indirizzo = ".../div(class=indirizzo)/";
            var rgs = doc.searchElement(div_indirizzo + "/h1");
            var street_adrs = doc.searchElement(div_indirizzo + ".../span(class=street-address)");
            var cap = doc.searchElement(div_indirizzo + ".../span(itemprop=postalCode)"); //Hanno messo a posto cap (dentro un apposito span) (CASAUALITA'?!?!?) AIUTO
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
    print("JSON - ELENCO N: " + current_page + "\n" + json);
//    doc = openDocument(doc_url + "&p=" + (current_page++));
//    print("CAMBIO PAGINA:" + doc.URL);
} while (current_page <= tot_page);

json = json.slice(0, json.length - 1) + "]}"; //cava l'ultima virgola (di eccesso, posta dopo l'ultima parentesi graffa, prima di chiudere il json

result = json;