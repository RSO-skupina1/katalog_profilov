package bean;

import entity.Profil;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.List;

@RequestScoped
public class ProfilBean {
    @PersistenceContext(unitName = "profils-jpa")
    private EntityManager em;

    public List<Profil> getProfils(){
        Query query = em.createNamedQuery("Profil.getAll", Profil.class);
        return query.getResultList();
    }

    public Profil getProfil(String profilId) {

        Profil profil = em.find(Profil.class, profilId);

        if (profil == null) {
            throw new NotFoundException();
        }

        return profil;
    }
    public Profil createProfil(Profil profil) {

        try {
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.persist(profil);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        return profil;
    }
    public Profil updateProfil(String profilId, Profil profil) {

        Profil u = em.find(Profil.class, profilId);

        if (u == null) {
            return null;
        }

        try {
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            profil.setId(u.getId());
            profil = em.merge(profil);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        return profil;
    }
    public boolean deleteProfil(String profilId) {

        Profil profil = em.find(Profil.class, profilId);

        if (profil != null) {
            try {
                if (!em.getTransaction().isActive())
                    em.getTransaction().begin();
                em.remove(profil);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        } else
            return false;

        return true;
    }
}
