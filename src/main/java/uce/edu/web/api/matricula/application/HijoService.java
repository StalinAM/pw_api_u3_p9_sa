package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infrastructure.HijoRepository;
import uce.edu.web.api.matricula.representation.HijoRepresentation;

@ApplicationScoped
public class HijoService {

    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorEstudianteId(Long estudianteId) {

        return this.hijoRepository.BuscarPorEstudianteId(estudianteId).stream()
                .map(this::mapperToHR)
                .toList();
    }

    private HijoRepresentation mapperToHR(Hijo hijo) {
        HijoRepresentation hijoR = new HijoRepresentation();
        hijoR.setId(hijo.getId());
        hijoR.setNombre(hijo.getNombre());
        hijoR.setApellido(hijo.getApellido());
        return hijoR;
    }
}
