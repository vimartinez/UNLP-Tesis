package ar.edu.unlp.tesis.processminingpostal2.controller;

import ar.edu.unlp.tesis.processminingpostal2.model.Reclamo;
import ar.edu.unlp.tesis.processminingpostal2.service.ReclamosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reclamos")
@CrossOrigin
public class ReclamosController {

    @Autowired
    ReclamosService reclamosService;

    @GetMapping(value="/")
    @Operation(summary="Devuelve un listado con todos los reclamos de una incidencia", description="No tiene parámetros de entrada", tags = {"Reclamos"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Se encontraron reclamos"),
            @ApiResponse(responseCode="404", description="No se encontraron reclamos en BD")
    })
    public @ResponseBody Map<String, Object> getAllReclamos( ) {

        List<Reclamo> reclamos = new ArrayList<Reclamo>();

        reclamos = reclamosService.getAllReclamos();
        Map<String, Object> response = new HashMap<>();
        response.put("reclamos", reclamos);
        response.put("currentPage",0);
        response.put("totalItems", 0);
        response.put("totalPages", 0);

        return response;
    }

    @PostMapping(value="/", produces= "application/json")
    @Operation(summary="Dar de alta un nuevo reclamo", description="Permite agregar un reclamo", tags = {"Reclamos"})
    public @ResponseBody void addReclamo(Reclamo reclamo){
        reclamosService.addReclamo(reclamo);

    }

    @GetMapping(value="/{idReclamo}")
    @Operation(summary="Obtener un reclamo de la base", description="Se debe enviar el id del reclamo", tags = {"Reclamos"})
    public @ResponseBody List<Reclamo> getReclamoById(@Parameter(description="id del reclamo") @PathVariable("idReclamo") Long id){
        return reclamosService.getReclamoById(id);

    }


}
