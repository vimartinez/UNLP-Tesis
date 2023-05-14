package ar.edu.unlp.tesis.processminingpostal2.repository;

import ar.edu.unlp.tesis.processminingpostal2.model.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReclamosRepository extends JpaRepository<Reclamo, Long> {


    @Query(nativeQuery=true, value = "SELECT id, case_id, rec_fecha FROM reclamos WHERE case_id= :case_id")
    List<Reclamo> getAllReclamosByCase_id(Long case_id);
}
