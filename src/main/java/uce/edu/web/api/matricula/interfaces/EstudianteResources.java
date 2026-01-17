package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResources {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> listarTodos() {
        return this.estudianteService.listarTodosLosEstudiantes();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Estudiante consultarPorId(@PathParam("id") Long id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("/guardar")
    public void guardarEstudiante(Estudiante estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizarEstudiante(@PathParam("id") Long id, Estudiante estudiante) {
        this.estudianteService.actualizarEstudiante(id, estudiante);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcialmenteEstudiante(@PathParam("id") Long id, Estudiante estudiante) {
        this.estudianteService.actualizarParcialmenteEstudiante(id, estudiante);
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void eliminarEstudiante(@PathParam("id") Long id) {
        this.estudianteService.eliminarEstudiante(id);
    }
}
