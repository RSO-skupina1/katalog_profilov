package entity;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

@Entity(name = "profil")
@NamedQueries(value =
        {
                @NamedQuery(name = "Profil.getAll", query = "SELECT p FROM profil p")
        })

@UuidGenerator(name = "idGenerator")
public class Profil {
    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "ime")
    private String ime;

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
}
