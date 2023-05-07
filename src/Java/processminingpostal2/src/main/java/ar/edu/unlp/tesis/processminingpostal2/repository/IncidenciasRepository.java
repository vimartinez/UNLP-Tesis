package ar.edu.unlp.tesis.processminingpostal2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unlp.tesis.processminingpostal2.model.Incidencia;


public interface IncidenciasRepository extends JpaRepository<Incidencia, Long>{
	
	@Query(nativeQuery=true, value = "SELECT id, duration_in_minutes, name, summary, year_of_release FROM movie WHERE year_of_release= :date")
    List<Incidencia> getAllIncidenciasByDate(Integer date);

}
