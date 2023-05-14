package ar.edu.unlp.tesis.processminingpostal2.repository;

import ar.edu.unlp.tesis.processminingpostal2.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnviosRepository extends JpaRepository<Envio, Long> {

    Optional<Envio> findBycaseId(Long id);
}
