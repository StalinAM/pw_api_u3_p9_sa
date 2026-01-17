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
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResources {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todas")
    public List<Materia> listarTodas() {
        return this.materiaService.listarTodasLasMaterias();
    }

    @GET
    @Path("/consultarPorCodigo/{codigo}")
    public Materia consultarPorCodigo(@PathParam("codigo") String codigo) {
        return this.materiaService.consultarPorCodigo(codigo);
    }

    @POST
    @Path("/guardar")
    public void guardarMateria(Materia materia) {
        this.materiaService.crearMateria(materia);
    }

    @PUT
    @Path("/actualizar/{codigo}")
    public void actualizarMateria(@PathParam("codigo") String codigo, Materia materia) {
        this.materiaService.actualizarMateria(codigo, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{codigo}")
    public void actualizarParcialmenteMateria(@PathParam("codigo") String codigo, Materia materia) {
        this.materiaService.actualizarParcialmenteMateria(codigo, materia);
    }

    @DELETE
    @Path("/eliminar/{codigo}")
    public void eliminarMateria(@PathParam("codigo") String codigo) {
        this.materiaService.eliminarMateria(codigo);
    }

    @GET
    @Path("/buscar/{texto}")
    public List<Materia> buscarPorTexto(@PathParam("texto") String texto) {
        return this.materiaService.buscarPorTexto(texto);
    }

    @GET
    @Path("/porFacultad/{facultad}")
    public List<Materia> materiasPorFacultad(@PathParam("facultad") String facultad) {
        return this.materiaService.materiasPorFacultad(facultad);
    }
}
