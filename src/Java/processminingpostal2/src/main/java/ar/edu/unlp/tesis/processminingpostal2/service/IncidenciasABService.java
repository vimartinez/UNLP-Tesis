package ar.edu.unlp.tesis.processminingpostal2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.unlp.tesis.processminingpostal2.model.TrazaAjusteBajo;
import ar.edu.unlp.tesis.processminingpostal2.repository.IncidenciasABRepository;

@Service
public class IncidenciasABService {

    @Autowired
    IncidenciasABRepository incidenciasABRepository;

    public TrazaAjusteBajo addIncidenciaAjusteBajo(TrazaAjusteBajo trazaAjusteBajo) {
        return incidenciasABRepository.save(trazaAjusteBajo);
    }

    public Optional<TrazaAjusteBajo> getIncidenciaAjusteBajoById(Long id) {
        return incidenciasABRepository.findById(id);
    }

    public void delIncidenciaAjusteBajo(Long id) {
        incidenciasABRepository.deleteById(id);
    }

    public void updIncidenciaAjusteBajo(TrazaAjusteBajo trazaAjusteBajo) {
        incidenciasABRepository.save(trazaAjusteBajo);
    }

    public Page<TrazaAjusteBajo> getAllIncidenciasAjusteBajo(Pageable paging) {
        return incidenciasABRepository.findAll(paging);
    }


    public Long count() {
        return incidenciasABRepository.countIncidenciasSinResolver();
    }
}
