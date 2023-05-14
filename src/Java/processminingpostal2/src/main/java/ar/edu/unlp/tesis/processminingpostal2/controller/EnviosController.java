package ar.edu.unlp.tesis.processminingpostal2.controller;

import ar.edu.unlp.tesis.processminingpostal2.model.Envio;
import ar.edu.unlp.tesis.processminingpostal2.service.EnviosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/envios")
@CrossOrigin

public class EnviosController {

    @Autowired
    EnviosService enviosService;


    @GetMapping(value="/{idEnvio}")
    @Operation(summary="Obtener un envio de la base", description="Se debe enviar el id del envio", tags = {"Envios"})
    public @ResponseBody Optional<Envio> getEnvioById(@Parameter(description="id del envio") @PathVariable("idEnvio") Long id){
        return enviosService.getEnvioById(id);

    }
}
