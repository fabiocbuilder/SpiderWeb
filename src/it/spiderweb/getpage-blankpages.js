/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Indica il numero di pagine totali
 */

var tot_page = document.searchElement("html/body/.../div(class=pag-group)/.../p(class=listing-pag-r)/b[1]"); //vado a pescarmi il numerino che indica il numero totale di pagine 

/*
 * Aggiungo un controllo per il numero totale di pagine (potrebbero giustamente non esserci più pagine per una ricerca)
 */
    if (tot_page !== null) {
        tot_page = tot_page.getInnerText();
    }
    else {
        tot_page = 1;
    }
    
result = tot_page;