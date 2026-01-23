package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import javax.print.attribute.standard.Media;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResources {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> listarTodos() {
        System.out.println("Listando todos los estudiantes");
        return this.estudianteService.listarTodosLosEstudiantes();
    }

    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id") Long id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("")
    public Response guardarEstudiante(Estudiante estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    public void actualizarEstudiante(@PathParam("id") Long id, Estudiante estudiante) {
        this.estudianteService.actualizarEstudiante(id, estudiante);
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarParcialmenteEstudiante(@PathParam("id") Long id, Estudiante estudiante) {
        this.estudianteService.actualizarParcialmenteEstudiante(id, estudiante);
        return Response.status(209).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    public void eliminarEstudiante(@PathParam("id") Long id) {
        this.estudianteService.eliminarEstudiante(id);
    }

    @GET
    @Path("/provincia")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        System.out.println("Buscando estudiantes por provincia: " + provincia);
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/provincia/genero")
    public List<Estudiante> buscarPorGenero(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorGenero(provincia, genero);
    }
}
