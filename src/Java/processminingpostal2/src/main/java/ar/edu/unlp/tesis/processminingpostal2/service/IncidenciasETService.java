package ar.edu.unlp.tesis.processminingpostal2.service;

import ar.edu.unlp.tesis.processminingpostal2.model.TrazaExcesoTiempo;
import ar.edu.unlp.tesis.processminingpostal2.repository.IncidenciasETRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidenciasETService {

    @Autowired
    IncidenciasETRepository incidenciasETRepository;


    public TrazaExcesoTiempo addIncidenciaExcesoTiempo(TrazaExcesoTiempo trazaAjusteBajo) {
        return incidenciasETRepository.save(trazaAjusteBajo);
    }

    public Optional<TrazaExcesoTiempo> getIncidenciaExcesoTiempoById(Long id) {
        return incidenciasETRepository.findById(id);
    }

    public void delIncidenciaExcesoTiempo(Long id) {
        incidenciasETRepository.deleteById(id);
    }

    public void updIncidenciaExcesoTiempo(TrazaExcesoTiempo trazaAjusteBajo) {
        incidenciasETRepository.save(trazaAjusteBajo);
    }

    public Page<TrazaExcesoTiempo> getAllIncidenciasExcesoTiempo(Pageable paging) {
        return incidenciasETRepository.findAll(paging);
    }

}
