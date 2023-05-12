package ar.edu.unlp.tesis.processminingpostal2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlp.tesis.processminingpostal2.model.TrazaAjusteBajo;
import org.springframework.data.jpa.repository.Query;


public interface IncidenciasABRepository extends JpaRepository<TrazaAjusteBajo, Long>{

    Page<TrazaAjusteBajo> findAll(Pageable pageable);

    @Query(nativeQuery=true, value = "SELECT COUNT(*) FROM trazasajustebajo WHERE resuelta= false")
    Long countIncidenciasSinResolver();
}
