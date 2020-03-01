package cat.copernic.ferranjuan.projectem07ferraniadrian;

public class Usuari {
    String nom;
    String Cognom;
    String usuari;
    String mail;
    String genere;

    public Usuari(String nom, String cognom, String usuari, String mail, String genere) {
        this.nom = nom;
        Cognom = cognom;
        this.usuari = usuari;
        this.mail = mail;
        this.genere = genere;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return Cognom;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getMail() {
        return mail;
    }

    public String getGenere() {
        return genere;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognom(String cognom) {
        Cognom = cognom;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
