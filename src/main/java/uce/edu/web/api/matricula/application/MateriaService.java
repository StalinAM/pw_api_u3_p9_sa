package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import java.util.List;

@ApplicationScoped
public class MateriaService {
    public List<Materia> listarTodasLasMaterias() {
        return Materia.listAll();
    }

    public Materia consultarPorCodigo(String codigo) {
        return Materia.find("codigo", codigo).firstResult();
    }

    @Transactional
    public void crearMateria(Materia materia) {
        materia.persist();
    }

    @Transactional
    public void actualizarMateria(String codigo, Materia materia) {
        Materia existente = consultarPorCodigo(codigo);
        if (existente != null) {
            existente.setNombre(materia.getNombre());
            existente.setCarrera(materia.getCarrera());
            existente.setFacultad(materia.getFacultad());
            existente.setHoras(materia.getHoras());
            existente.setModalidad(materia.getModalidad());
        }
    }

    @Transactional
    public void actualizarParcialmenteMateria(String codigo, Materia materia) {
        Materia existente = consultarPorCodigo(codigo);
        if (existente != null) {
            if (materia.getNombre() != null)
                existente.setNombre(materia.getNombre());
            if (materia.getCarrera() != null)
                existente.setCarrera(materia.getCarrera());
            if (materia.getFacultad() != null)
                existente.setFacultad(materia.getFacultad());
            if (materia.getHoras() != null)
                existente.setHoras(materia.getHoras());
            if (materia.getModalidad() != null)
                existente.setModalidad(materia.getModalidad());
        }
    }

    @Transactional
    public void eliminarMateria(String codigo) {
        Materia existente = consultarPorCodigo(codigo);
        if (existente != null) {
            existente.delete();
        }
    }

    public List<Materia> buscarPorTexto(String texto) {
        String like = "%" + texto.toLowerCase() + "%";
        return Materia.find("lower(nombre) like ?1 or lower(carrera) like ?1", like).list();
    }

    @SuppressWarnings("unchecked")
    public List<Materia> materiasPorFacultad(String facultad) {
        return (List<Materia>) Materia.getEntityManager()
                .createNativeQuery("SELECT * FROM materia WHERE facultad = ?1", Materia.class)
                .setParameter(1, facultad)
                .getResultList();
    }
}
