/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Funzione che cerca il numero di pagine totali per una ricerca singola
 */
function getTotPage(){
    var tot_page = document.searchElement("html/body/.../div(class=pag-group)/.../p(class=listing-pag-r)/b[1]"); //vado a pescarmi il numerino che indica il numero totale di pagine 
    if (tot_page !== null) {
        tot_page = tot_page.getInnerText();
    }
    else {
        tot_page = 1;
    }
    return tot_page;
}

result = getTotPage();