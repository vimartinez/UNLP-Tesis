package ar.edu.unlp.tesis.processminingpostal2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlp.tesis.processminingpostal2.model.Traza;
import ar.edu.unlp.tesis.processminingpostal2.repository.TrazasRepository;

@Service
public class TrazasService {

	 @Autowired
	    TrazasRepository trazasRepository;
	    public List<Traza> getAllTrazas() {
	        return trazasRepository.findAll();
	    }

	    public Traza addTraza(Traza traza) {
	        return trazasRepository.save(traza);
	    }

	    public Optional<Traza> getTrazaById(Long id) {
	        return trazasRepository.findById(id);
	    }

	    public void delTraza(Long id) {
	        trazasRepository.deleteById(id);
	    }

	    public void updTraza(Traza traza) {
	        trazasRepository.save(traza);
	    }

	    public List<Traza> getAllTrazasByDate(Integer fecha) {
	        return trazasRepository.getAllTrazasByDate(fecha);
	    }
}
