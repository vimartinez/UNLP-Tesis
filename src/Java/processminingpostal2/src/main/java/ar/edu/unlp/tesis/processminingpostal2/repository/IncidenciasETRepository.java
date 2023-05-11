package ar.edu.unlp.tesis.processminingpostal2.repository;

import ar.edu.unlp.tesis.processminingpostal2.model.TrazaExcesoTiempo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IncidenciasETRepository extends JpaRepository<TrazaExcesoTiempo, Long>{

    Page<TrazaExcesoTiempo> findAll(Pageable pageable);


}
