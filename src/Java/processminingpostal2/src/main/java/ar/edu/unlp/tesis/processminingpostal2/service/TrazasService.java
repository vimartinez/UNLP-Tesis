package ar.edu.unlp.tesis.processminingpostal2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.unlp.tesis.processminingpostal2.model.Traza;
import ar.edu.unlp.tesis.processminingpostal2.repository.TrazasRepository;

@Service
public class TrazasService {

	 @Autowired
	    TrazasRepository trazasRepository;
	    public List<Traza> getAllTrazas() {
		//	Pageable secondPageWithFiveElements = (Pageable) PageRequest.of(1, 5);
	    //    return trazasRepository.findAll(secondPageWithFiveElements);
			return trazasRepository.findAll();
	    }

	    public Traza addTraza(Traza traza) {
	        return trazasRepository.save(traza);
	    }

	    public List<Traza> getTrazaById(Long case_id) {
	        return trazasRepository.getAllTrazasByCase_id(String.valueOf(case_id));
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


	public Page<Traza> findAll(Pageable pagina) {
			return trazasRepository.findAll(pagina);
	}
}
