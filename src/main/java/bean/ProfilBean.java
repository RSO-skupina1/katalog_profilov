package bean;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import entity.Profil;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import jersey.repackaged.com.google.common.base.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
public class ProfilBean {
    @Inject
    @DiscoverService(value = "sporocilni-sistem", environment = "dev", version = "*")
    private Optional<String> basePath;

    @PersistenceContext(unitName = "profil-jpa")
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

    public List<Profil> getCustomersFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        List<Profil> customers = JPAUtils.queryEntities(em, Profil.class, queryParameters);

        return customers;
    }
}
