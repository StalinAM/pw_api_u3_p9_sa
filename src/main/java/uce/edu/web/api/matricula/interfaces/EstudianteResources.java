package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.representation.HijoRepresentation;
import uce.edu.web.api.matricula.representation.LinkDTO;

@Path("/estudiantes")
public class EstudianteResources {
    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> estudiantes = this.estudianteService.listarTodosLosEstudiantes();
        return estudiantes.stream().map(this::construirLinks).toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstudianteRepresentation consultarPorId(@PathParam("id") Long id) {
        return this.construirLinks(this.estudianteService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarEstudiante(EstudianteRepresentation estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarEstudiante(@PathParam("id") Long id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarEstudiante(id, estudiante);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcialmenteEstudiante(@PathParam("id") Long id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarParcialmenteEstudiante(id, estudiante);
        return Response.status(209).entity(this.estudianteService.consultarPorId(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void eliminarEstudiante(@PathParam("id") Long id) {
        this.estudianteService.eliminarEstudiante(id);
    }

    @GET
    @Path("/provincia")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> buscarPorGenero(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorGenero(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HijoRepresentation> buscarHijosPorEstudianteId(@PathParam("id") Long id) {
        return this.hijoService.buscarPorEstudianteId(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation estudiante) {
        String baseUri = this.uriInfo.getBaseUriBuilder().path(EstudianteResources.class)
                .path(String.valueOf(estudiante.getId())).build().toString();
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResources.class)
                .path(String.valueOf(estudiante.getId())).build().toString();
        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResources.class)
                .path(String.valueOf(estudiante.getId())).path("hijos").build().toString();

        LinkDTO selfLink = new LinkDTO(baseUri, "self");
        LinkDTO hijosLink = new LinkDTO(hijos, "hijos");

        estudiante.setLinks(List.of(selfLink, hijosLink));
        return estudiante;
    }

}
