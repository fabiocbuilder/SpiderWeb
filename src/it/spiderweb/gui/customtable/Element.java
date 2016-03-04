package it.spiderweb.gui.customtable;

@JTBean
public class Element {

    @FieldProperties(name = "Azienda", size = 90)
    private String rgs = "";

    @FieldProperties(name = "Indirizzo, Via", size = 90)
    private String address = "";

    @FieldProperties(name = "Comune", size = 90)
    private String district = "";

    @FieldProperties(name = "Provincia", size = 90)
    private String territory = "";

    @FieldProperties(name = "CAP", size = 90)
    private String cap = "";

    @FieldProperties(name = "Telefono", size = 90)
    private String tel = "";

    @FieldProperties(name = "Fax", size = 90)
    private String fax = "";

    @FieldProperties(name = "Sito Web", size = 90)
    private String webiste = "";

    @FieldProperties(name = "Email", size = 90)
    private String email = "";

    @DefaultFieldValue(defaultStringValue = "sample value")
    public String getRgs() {
        return rgs;
    }

    public void setRgs(String rgs) {
        this.rgs = rgs;
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

    @Override
    public String toString() {
        String output;
        output = "Ragione sociale: " + getRgs() + "\n"
                + "Indirizzo primario: " + getAddress() + "\n"
                + "Comune: " + getDistrict() + "\n"
                + "Provincia: " + getTerritory() + "\n"
                + "CAP: " + getCap() + "\n"
                + "Telefono: " + getTel() + "\n"
                + "Fax: " + getFax() + "\n"
                + "Sito Web: " + getWebsite() + "\n"
                + "Email: " + getEmail();
        return output;
    }

}
