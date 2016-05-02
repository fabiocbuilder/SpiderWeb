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
            case address:
                var text = tag.getInnerText();
                var n_civico = text.substring(0, text.indexOf(","));
                text = cut(tag, ",");
                output = text + "," + n_civico;
            case territory:
                var text = tag.getInnerText();
                output = text.substring(text.indexOf("(") + 1, text.indexOf(")"));
                break;
            case tel:
                var numbers = tel.getElementCount();
                switch (numbers) {
                    case 2:
                        tel = tel[0];
                        fax = tel[1];
                        break;
                    default:
                        tel = tel[0];
                        fax = "";
                        break;
                }
            case website:
                output = tag.getLinkURL();
            default:
                output = tag.getInnerText();
        }
    }
    return output;
}

/**
 * Mette in pausa il thread in esecuzione secondo il parametro specificato (in millisecondi)
 * Riferimento: http://stackoverflow.com/questions/16623852/how-to-pause-javascript-code-excution-for-2-seconds
 * @param {type} miliseconds
 * @returns {undefined}
 */
function sleep(miliseconds) {
    var currentTime = new Date().getTime();
    while (currentTime + miliseconds >= new Date().getTime()) {
    }
}

/**
 * Cerca, a partire da uno specifico URL, un'email dentro al footer di questa 
 * pagina web. 
 * Restituisce la email, trovata o non trovata ("")
 * @param {type} url
 * @returns {innerGrabLogic.text|String}
 */
function innerGrabLogic(url) {
    var email = "";
    doc = openDocument(url.getLinkURL()); //apre il link
    var div_list = doc.searchElements("html/body/div*"); //prende tutti i tag <div> dentro a questa pagina web
    for (var i = 0; i < div_list.length; i++) { //li itera
        var attribute = div_list[i].getAttribute("class"); //per ognuno, prende l'attributo "class"
        if (attribute.indexOf("footer") > -1) { //se l'attributo class contiene la sottostringa "footer"
            var div = div_list[i]; //prende il divisore
            var a_list = div.searchElements(".../a*"); //di questo <div> ne cerca tutti i tag <a> (perchè l'email è solita essere dentro un tag <a>)
            for (var c = 0; c < a_list.length; c++) { //itera i tag <a>
                var text = a_list[c].getInnerText(); //ne prende il contenuto testuale
                if (text.indexOf("@") > -1) { //se il contenuto testuale contiene la "chiocciola"
                    email = text; //salva l'email nel suo attributo ed esce
                    i = div_list.legnth; //imposta i dati per uscire
                    break;
                }
            }
        }
    }
    return email;
}

/**
 * Naviga la pagina principale di riferimento. 
 * Entra dentro ai link delle singole aziende, prende le informazioni, esce e
 * rientra nella successiva, fino al termine della lista.
 * Dopodichè guarda se ci sono eventuali pagine successive, se si, prosegue fino 
 * a terminarle, altrimento si ferma.
 * Inserisce ogni informazione in un json strutturato.
 * Ritorna il json finito
 * @returns {String} json
 */
function search() {
    handler.update();
    do {
        doc = openDocument(doc_url + "/p-" + current_page++);
        var list = doc.searchElements("html/body/div(class=container)/.../div(id=listLeft)/div(class=listElements)/div(class=listElementsInnerWrapper)"); //path più preciso per una miglior performance
        for (var i = 0; i < list.length; i++) {
            var div_indirizzo = ".../div(class=elementRowL/)";
            rgs = list[i].searchElement(div_indirizzo + "div(class=elementTop clearfix)/.../span(class=fn elementTitle)");
            address = list[i].searchElement(div_indirizzo + "div(class=elementBody clearfix)/.../span(class=street-address");
            cap = list[i].searchElement(div_indirizzo + "div(class=elementBody clearfix)/.../span(class=postal-code");
            district = list[i].searchElement(div_indirizzo + "div(class=elementBody clearfix)/.../span(class=locality)");
            territory = list[i].searchElement(div_indirizzo + "div(class=elementBody clearfix)/.../span(class=region)");
            tel = list[i].searchElements(div_indirizzo + "div(class=elementBody clearfix)/.../div(class=tel elementPhone)"); //tel è un tag <div>, per gestire più numeri di tel, fax viene riempito di conseguenza. @see format(tag);
            website = list[i].searchElement("div(class=elementBody clearfix)/.../div(class=elementLinks)/a(title=sito web)");
            email = innerGrabLogic(website);
            json = "{\"rgs\":" + "\"" + format(rgs) + "\"" + "," +
                    "\"address\":" + "\"" + format(address) + "\"" + "," +
                    "\"district\":" + "\"" + format(district) + "\"" + "," +
                    "\"territory\":" + "\"" + format(territory) + "\"" + "," +
                    "\"cap\":" + "\"" + format(cap) + "\"" + "," +
                    "\"tel\":" + "\"" + format(tel) + "\"" + "," +
                    "\"fax\":" + "\"" + format(fax) + "\"" + "," +
                    "\"website\":" + "\"" + format(website) + "\"" + "," +
                    "\"email\":" + "\"" + format(email) + "\"" + "}" + ",";
            handler.update(json.substring(0, json.length - 1));
            sleep(2000);
        }
        sleep(8000);
    } while (true);
    return "";
}

result = search();