/* 
 * Logica dinamica per analizzare la pagina web
 */

print("inizio");

var append = "";

//questo è l'oggetto che mi rappresenta un tag secondo un detemrinato criterio:
//"element" è il tag vero e proprio, "pos" è il riferimento all'elemento
//interno al tag da analizzare successivamente.
function Tag(element, pos) {
    this.element = element;
    this.pos = pos;
}

var tags = [];

//var elements = document.searchElements("html/body/div");
//potrebbe essere un pò pesante per le performance dover cercare 
//in tutti i tag le informazioni del footer, sapendo che nella maggior
//parte dei casi si trovano nell'ultimo div, ma non si sa mai...
var elements = document.searchElements("html/body/.../div(class=*footer*)");

for (var i = 0; i < elements.length; i++) {
    print(elements[i].getAttribute("class"));
    grabInformations(new Tag(elements[i], elements[i].getElementCount()));
}

//cerca ricorsivamente le informazioni all'interno dei sotto elementi del tag
//padre con il quale è stato chiamato.
function grabInformations(tag) {

    //il tag passato ha o non ha figli? Oppure, ha ancora posizioni disponibili?
    if (tag.pos > 0) {
        print("Tag: " + tag.element.getTagName());
        //prendo l'elemento alla posizione "pos" del suo elemento padre
        var innerElement = tag.element.getElement(tag.pos);

        //a partire da quest'ultimo elemento, mi reo il rispettivo tag con
        //tanto di numero di elementi presenti al suo interno.
        var newTag = new Tag(innerElement, innerElement.getElementCount());

        //questo tag appena creato lo ficco dentro all'array, in questo modo
        //potro utilizzarlo nuovamente per esplorare gli altri suoi tag figli
        tags.push(newTag);

        //richiamo questa stessa funzione con il nuovo tag appena creato così
        //da poter esplorare i suoi tag interni con la stessa logica spiegata
        //nei precedenti commenti
        grabInformations(newTag);
    } else {

        //allora vuole dire che dentro a questo tag ci potrebbe essere del testo,
        //quindi me lo prendo (il testo).
        append += tag.element.getInnerText() + "; ";

        //e poi torno su al tag padre
        grabInformations(
                findTagAndDecreasePos(
                        new Tag(
                                tag.element.getParentElement(),
                                tag.element.getElementCount()
                                )
                        )
                );
    }
}

//cerca all'interno dell'array "tags" l'elemento tag apassato. Trovandolo, gli
//decrementa l'indice "pos".
function findTagAndDecreasePos(tagToFind) {
    for (var i = 0; i < tags.length; i++) {

        //è una variabile d'appoggio
        var currTag = tags[i];

        //ogni elemento dell'array viene confrontato con l'elemento passato
        //come parametro. Se viene trovato, gli si decrementa la posizione e 
        //lo si ritorna. Ovviamente si aggiorna anche il record nell'array.
        if (_.isMatch(currTag.element, tagToFind.element)) {
            tags[i] = new Tag(currTag.tagName, (currTag.pos - 1));
            return tags[i];
        }
    }
}

result = append;

//questo è l'esempio utilizzato per progettare e testare lo script all'interno di questo file
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

< script src = "../underscore-min.js" > 