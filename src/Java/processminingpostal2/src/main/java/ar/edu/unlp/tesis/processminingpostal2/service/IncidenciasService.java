package ar.edu.unlp.tesis.processminingpostal2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlp.tesis.processminingpostal2.model.Incidencia;
import ar.edu.unlp.tesis.processminingpostal2.repository.IncidenciasRepository;

@Service
public class IncidenciasService {

    @Autowired
    IncidenciasRepository incidenciasRepository;
    public List<Incidencia> getAllIncidencias() {
        return incidenciasRepository.findAll();
    }

    public Incidencia addIncidencia(Incidencia incidencia) {
        return incidenciasRepository.save(incidencia);
    }

    public Optional<Incidencia> getIncidenciaById(Long id) {
        return incidenciasRepository.findById(id);
    }

    public void delIncidencia(Long id) {
        incidenciasRepository.deleteById(id);
    }

    public void updIncidencia(Incidencia incidencia) {
        incidenciasRepository.save(incidencia);
    }

    public List<Incidencia> getAllIncidenciasByDate(Integer fecha) {
        return incidenciasRepository.getAllIncidenciasByDate(fecha);
    }
}
