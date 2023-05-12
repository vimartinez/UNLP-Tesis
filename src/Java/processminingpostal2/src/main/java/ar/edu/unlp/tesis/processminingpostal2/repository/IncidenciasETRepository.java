package ar.edu.unlp.tesis.processminingpostal2.repository;

import ar.edu.unlp.tesis.processminingpostal2.model.TrazaExcesoTiempo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IncidenciasETRepository extends JpaRepository<TrazaExcesoTiempo, Long>{

    Page<TrazaExcesoTiempo> findAll(Pageable pageable);

    @Query(nativeQuery=true, value = "SELECT COUNT(*) FROM trazasexcesotiempo WHERE resuelta= false")
    Long countIncidenciasSinResolver();


}
