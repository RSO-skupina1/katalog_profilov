package rest;

import bean.ProfilBean;
import entity.Profil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("katalog_profilov")
public class KatalogProfilovREST {

    @Inject
    private ProfilBean profilBean;

    @GET
    @Path("/test")
    public Response test() {
        return Response.ok("OK").build();
    }

    @GET
    public Response getProfils() {

        List<Profil> profils = profilBean.getProfils();

        return Response.ok(profils).build();
    }

    @GET
    @Path("/{profilId}")
    public Response getProfil(@PathParam("profilId") String profilId) {

        Profil profil = profilBean.getProfil(profilId);

        if (profil == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(profil).build();
    }

    @POST
    public Response createProfil(Profil profil) {

        if ((profil.getIme() == null || profil.getIme().isEmpty()) || (profil.getPriimek() == null
                || profil.getPriimek().isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            profil = profilBean.createProfil(profil);
        }

        if (profil.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(profil).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(profil).build();
        }
    }

    @PUT
    @Path("{customerId}")
    public Response updateProfil(@PathParam("profilId") String profilId, Profil profil) {

        profil = profilBean.updateProfil(profilId, profil);

        if (profil == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (profil.getId() != null)
                return Response.status(Response.Status.OK).entity(profil).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{profilId}")
    public Response deleteProfil(@PathParam("profilId") String profilId) {

        boolean deleted = profilBean.deleteProfil(profilId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
