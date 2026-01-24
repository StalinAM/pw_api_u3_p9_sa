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
    public void crearEstudiante(Estudiante estudiante) {
        this.estudianteRepository.persist(estudiante);
    }

    @Transactional
    public void actualizarEstudiante(Long id, EstudianteRepresentation estudiante) {
        Estudiante est = this.mapperToEstudiante(this.consultarPorId(id));
        if (est != null) {
            est.setNombre(estudiante.getNombre());
            est.setApellido(estudiante.getApellido());
            est.setFechaNacimiento(estudiante.getFechaNacimiento());
            // La entidad est se actualiza automáticamente por dirty checking
        }
    }

    @Transactional
    public void actualizarParcialmenteEstudiante(Long id, EstudianteRepresentation estudiante) {
        Estudiante est = this.mapperToEstudiante(this.consultarPorId(id));
        if (estudiante.getNombre() != null) {
            est.setNombre(estudiante.getNombre());
        }
        if (estudiante.getApellido() != null) {
            est.setApellido(estudiante.getApellido());
        }
        if (estudiante.getFechaNacimiento() != null) {
            est.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        // La entidad est se actualiza automáticamente por dirty checking
    }

    @Transactional
    public void eliminarEstudiante(Long id) {
        Estudiante est = this.mapperToEstudiante(this.consultarPorId(id));
        if (est != null) {
            this.estudianteRepository.delete(est);
        }
    }

    public List<Estudiante> buscarPorProvincia(String provincia) {
        return this.estudianteRepository.find("provincia", provincia).list();
    }

    public List<Estudiante> buscarPorGenero(String provincia, String genero) {
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
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
