/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.spiderweb.bl;

/**
 *
 * @author agrimandi
 */
public class TableElement {

    private String rgs;
    private String address;
    private String district;
    private String territory;
    private String cap;
    private String tel;
    private String fax;
    private String website;
    private String email;

    public TableElement() {}

    public TableElement(String rgs, String address, String district, String territory, String cap, String tel, String fax, String website, String email) {
        this.rgs = rgs;
        this.address = address;
        this.district = district;
        this.territory = territory;
        this.cap = cap;
        this.tel = tel;
        this.fax = fax;
        this.website = website;
        this.email = email;
    }

    public String getRgs() {
        return rgs;
    }

    public void setRgs(String rgs) {
        this.rgs = rgs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        String output;
        output = "Ragione sociale: " + getRgs() + "\n" +
                 "Indirizzo primario: " + getAddress() + "\n" +
                 "Comune: " + getDistrict() + "\n" +
                 "Provincia: " + getTerritory() + "\n" +
                 "CAP: " + getCap() + "\n" +
                 "Telefono: " + getTel() + "\n" +
                 "Fax: " + getFax() + "\n" +
                 "Sito Web: " + getWebsite() + "\n" +
                 "Email: " + getEmail();
        return output;
    }
    
}
