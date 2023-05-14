package ar.edu.unlp.tesis.processminingpostal2.service;

import ar.edu.unlp.tesis.processminingpostal2.model.Reclamo;
import ar.edu.unlp.tesis.processminingpostal2.repository.ReclamosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamosService {

    @Autowired
    ReclamosRepository reclamosRepository;
    public List<Reclamo> getAllReclamos() {
        return reclamosRepository.findAll();
    }

    public List<Reclamo> getReclamoById(Long id) {
        return reclamosRepository.getAllReclamosByCase_id(id);
    }

    public void addReclamo(Reclamo reclamo) {
        reclamosRepository.save(reclamo);
    }
}
