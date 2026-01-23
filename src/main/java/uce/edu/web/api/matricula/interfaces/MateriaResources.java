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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResources {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> listarTodas() {
        return this.materiaService.listarTodasLasMaterias();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_XML)
    public Materia consultarPorCodigo(@PathParam("codigo") String codigo) {
        return this.materiaService.consultarPorCodigo(codigo);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarMateria(Materia materia) {
        this.materiaService.crearMateria(materia);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarMateria(@PathParam("codigo") String codigo, Materia materia) {
        this.materiaService.actualizarMateria(codigo, materia);
        return Response.status(209).entity(this.consultarPorCodigo(codigo)).build();
    }

    @PATCH
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcialmenteMateria(@PathParam("codigo") String codigo, Materia materia) {
        this.materiaService.actualizarParcialmenteMateria(codigo, materia);
        return Response.status(209).entity(this.consultarPorCodigo(codigo)).build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response eliminarMateria(@PathParam("codigo") String codigo) {
        this.materiaService.eliminarMateria(codigo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/texto/{texto}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> buscarPorTexto(@PathParam("texto") String texto) {
        return this.materiaService.buscarPorTexto(texto);
    }

    @GET
    @Path("/facultad/{facultad}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> materiasPorFacultad(@PathParam("facultad") String facultad) {
        return this.materiaService.materiasPorFacultad(facultad);
    }
}
