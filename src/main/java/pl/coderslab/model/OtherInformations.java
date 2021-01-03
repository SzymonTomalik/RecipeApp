package pl.coderslab.model;

public class OtherInformations {

    int id;
    String companyName, companyAdress,companyEmail,companyPhone,authors;

    public OtherInformations(int id, String companyName, String companyAdress, String companyEmail, String companyPhone, String authors) {
        this.id = id;
        this.companyName = companyName;
        this.companyAdress = companyAdress;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.authors = authors;
    }

    public OtherInformations() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
