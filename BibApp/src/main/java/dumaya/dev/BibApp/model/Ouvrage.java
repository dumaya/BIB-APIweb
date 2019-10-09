package dumaya.dev.BibApp.model;

import javax.persistence.*;

@Entity
@Table(name = "ouvrage")
public class Ouvrage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ouvrage_id")
    private int id;
    @Column(name="id_reference")
    private int idReference;
    @Column(name="emplacement")
    private String emplacement;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public int getIdReference() {
        return idReference;
    }

    public void setIdReference(int idReference) {
        this.idReference = idReference;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

}
