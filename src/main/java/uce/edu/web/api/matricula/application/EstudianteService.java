package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infrastructure.EstudianteRepository;
import uce.edu.web.api.matricula.representation.EstudianteRepresentation;

@ApplicationScoped

public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodosLosEstudiantes() {
        List<Estudiante> estudiantes = this.estudianteRepository.listAll();
        return estudiantes.stream()
                .map(this::mapperToER)
                .toList();
    }

    public EstudianteRepresentation consultarPorId(Long id) {
        Estudiante estudiante = this.estudianteRepository.findById(id);
        return this.mapperToER(estudiante);
    }

    @Transactional
    public void crearEstudiante(EstudianteRepresentation estudianteR) {
        Estudiante estudiante = this.mapperToEstudiante(estudianteR);
        this.estudianteRepository.persist(estudiante);
    }

    @Transactional
    public void actualizarEstudiante(Long id, EstudianteRepresentation estudianteR) {
        Estudiante est = this.estudianteRepository.findById(id);
        if (est != null) {
            if (estudianteR.getNombre() != null)
                est.setNombre(estudianteR.getNombre());
            if (estudianteR.getApellido() != null)
                est.setApellido(estudianteR.getApellido());
            if (estudianteR.getFechaNacimiento() != null)
                est.setFechaNacimiento(estudianteR.getFechaNacimiento());
            if (estudianteR.getProvincia() != null)
                est.setProvincia(estudianteR.getProvincia());
            if (estudianteR.getGenero() != null)
                est.setGenero(estudianteR.getGenero());
        }
    }

    @Transactional
    public void actualizarParcialmenteEstudiante(Long id, EstudianteRepresentation estudianteR) {
        Estudiante est = this.estudianteRepository.findById(id);
        if (est != null) {
            if (estudianteR.getNombre() != null)
                est.setNombre(estudianteR.getNombre());
            if (estudianteR.getApellido() != null)
                est.setApellido(estudianteR.getApellido());
            if (estudianteR.getFechaNacimiento() != null)
                est.setFechaNacimiento(estudianteR.getFechaNacimiento());
            if (estudianteR.getProvincia() != null)
                est.setProvincia(estudianteR.getProvincia());
            if (estudianteR.getGenero() != null)
                est.setGenero(estudianteR.getGenero());
        }
    }

    @Transactional
    public void eliminarEstudiante(Long id) {
        Estudiante est = this.estudianteRepository.findById(id);
        if (est != null) {
            this.estudianteRepository.delete(est);
        }
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia) {
        List<Estudiante> estudiantes = this.estudianteRepository.find("provincia", provincia).list();
        return estudiantes.stream().map(this::mapperToER).toList();
    }

    public List<EstudianteRepresentation> buscarPorGenero(String provincia, String genero) {
        List<Estudiante> estudiantes = this.estudianteRepository
                .find("provincia = ?1 and genero = ?2", provincia, genero).list();
        return estudiantes.stream().map(this::mapperToER).toList();
    }

    private EstudianteRepresentation mapperToER(Estudiante estudiante) {
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.setId(estudiante.getId());
        estuR.setNombre(estudiante.getNombre());
        estuR.setApellido(estudiante.getApellido());
        estuR.setFechaNacimiento(estudiante.getFechaNacimiento());
        estuR.setProvincia(estudiante.getProvincia());
        estuR.setGenero(estudiante.getGenero());
        return estuR;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation estuR) {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(estuR.getId());
        estudiante.setNombre(estuR.getNombre());
        estudiante.setApellido(estuR.getApellido());
        estudiante.setFechaNacimiento(estuR.getFechaNacimiento());
        estudiante.setProvincia(estuR.getProvincia());
        estudiante.setGenero(estuR.getGenero());
        return estudiante;
    }

}
