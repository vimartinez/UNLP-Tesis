package ar.edu.unlp.tesis.processminingpostal2.service;

import ar.edu.unlp.tesis.processminingpostal2.model.Envio;
import ar.edu.unlp.tesis.processminingpostal2.repository.EnviosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnviosService {

    @Autowired
    EnviosRepository enviosRepository;
    public Optional<Envio> getEnvioById(Long id) {
        return enviosRepository.findBycaseId(id);
    }
}
