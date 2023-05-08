package ar.edu.unlp.tesis.processminingpostal2.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unlp.tesis.processminingpostal2.model.Traza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrazasRepository  extends JpaRepository<Traza, Long>  {

//    List<Traza> findAll(Pageable pageable);
    @Query(nativeQuery=true, value = "SELECT id, case_id, activity, timestamp, activityid FROM trazas WHERE case_id= :case_id")
    List<Traza> getAllTrazasByCase_id(String case_id);
	@Query(nativeQuery=true, value = "SELECT id, activity, timestamp, activityid, FROM trazas WHERE timestamp= :date")
    List<Traza> getAllTrazasByDate(Integer date);

}
