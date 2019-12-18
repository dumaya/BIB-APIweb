package dumaya.dev.BibApp.model;

import javax.persistence.*;

@Entity
@Table(name = "usager")
public class Usager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String mail;

    public int getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(int idWeb) {
        this.idWeb = idWeb;
    }

    @Column
    private int idWeb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
