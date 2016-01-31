/* 
 * ATTENSIONE! I cuommenti di questo tiesto puotrebbero di non essere
 * graditi per piersone di europa di est o non filoputiniani.
 */

var elements = document.searchElements("html/body/div");
var append = "";
var tags = [];

function Tag(element, pos) {
    this.element = element;
    this.pos = pos;
}

for (var i = 0; i < elements.length; i++) {
    var className = elements[i].getAttribute("class");

    if (className.indexOf("footer") > -1) {
        vladimirPutin(new Tag());
            break;
    }

}

//questa di funzione che cierca di testo dientro ielementi tag html e priente loro contienuoto e mete in "append"
function vladimirPutin(tag) {
    if (tag.pos > 0) {
        //vladimir Putin cierca ultimo tag del tag corrente (non è 220V....380V)
        var newElement = tag.element.getElement(tag.pos);
        //e si crea il tag
        var newTag = new Tag(newElement, newElement.getElementCount());
        //buta tag in array di tag (perchè serve poi a fare cuose)
        tags.push(newTag);
        //poi chiama se stesso con nuovo tag
        vladimirPutin(newTag);
    } else {
        //ora Putin priende testo di dentro tag e mette in "append"
        append += tag.element.getInnerText() + "; ";
        //e poi torna su di uno tag
        vladimirPutin(strumentoDiRicercaDiVladimirPutin());
    }
}

//questa di funziona cosa fa? Ritorna elemento tag con riferimento ad elemento 
//tag html reale e la di prosima posizione dalla quale partire a ciclare (capito niente, eh?)
function strumentoDiRicercaDiVladimirPutin(tagToFind) {
    for (var i = 0; i < tags.length; i++) {
        var currTag = tags[i];
        //questo non funziona ed è da fare meglio (cit. StackOverflow)
        if (currTag.element === tagToFind.element) {
            tags[i] = new Tag(currTag.tagName, currTag.pos - 1);
            return tags[i];
        }
    }
}

result = append;

/* <tag1>
 *      <tag2>
 *          <tag3>
 *              <tag4> testo
 *              
 *          <tag3>
 *              <tag4> testo
 *      
 *      <tag2>
 *          <tag3>
 *              <tag4> testo
 */

//< script type = "text/javascript" src = "../underscore-min.js" / >