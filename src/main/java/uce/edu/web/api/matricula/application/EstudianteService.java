package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infrastructure.EstudianteRepository;

@ApplicationScoped

public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodosLosEstudiantes() {
        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorId(Long id) {
        return this.estudianteRepository.findById(id);
    }

    @Transactional
    public void crearEstudiante(Estudiante estudiante) {
        this.estudianteRepository.persist(estudiante);
    }

    @Transactional
    public void actualizarEstudiante(Long id, Estudiante estudiante) {
        Estudiante est = this.consultarPorId(id);
        if (est != null) {
            est.setNombre(estudiante.getNombre());
            est.setApellido(estudiante.getApellido());
            est.setFechaNacimiento(estudiante.getFechaNacimiento());
            // La entidad est se actualiza automáticamente por dirty checking
        }
    }

    @Transactional
    public void actualizarParcialmenteEstudiante(Long id, Estudiante estudiante) {
        Estudiante est = this.consultarPorId(id);
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
        Estudiante est = this.consultarPorId(id);
        if (est != null) {
            this.estudianteRepository.delete(est);
        }
    }
}
