package it.spiderweb.gui.customtable;

@JTBean
public class Element {

    @FieldProperties(name = "Azienda", size = 90)
    private String company = null;

    @FieldProperties(name = "Indirizzo, Via", size = 90)
    private String address = null;

    @FieldProperties(name = "Comune", size = 90)
    private String district = null;

    @FieldProperties(name = "Provincia", size = 90)
    private String territory = null;

    @FieldProperties(name = "CAP", size = 90)
    private String cap = null;

    @FieldProperties(name = "Nazione", size = 90)
    private String nation = null;

    @FieldProperties(name = "Telefono", size = 90)
    private String tel = null;

    @FieldProperties(name = "Fax", size = 90)
    private String fax = null;

    @FieldProperties(name = "Sito Web", size = 90)
    private String webiste = null;

    @FieldProperties(name = "Email", size = 90)
    private String email = null;

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getWebsite() {
        return webiste;
    }

    public void setWebsite(String webiste) {
        this.webiste = webiste;
    }

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
