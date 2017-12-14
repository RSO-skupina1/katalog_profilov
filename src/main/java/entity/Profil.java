package entity;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "profil")
@NamedQueries(value =
        {
                @NamedQuery(name = "Profil.getAll", query = "SELECT p FROM Profil p")
        })

@UuidGenerator(name = "idGenerator")
public class Profil implements Serializable {
    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "ime")
    private String ime;  //ime uporabnika

    @Column(name = "priimek")
    private String priimek;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    @Override
    public String toString() {
        return ("id: " + this.id + ", " +
                "ime: '" + this.ime +
                "', priimek: " + this.priimek);
    }
}
