package ar.edu.unlp.tesis.processminingpostal2.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import ar.edu.unlp.tesis.processminingpostal2.model.TrazaAjusteBajo;
import ar.edu.unlp.tesis.processminingpostal2.service.IncidenciasABService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/incidencias")
@CrossOrigin
public class IncidenciasABController {
	
	@Autowired
    IncidenciasABService incidenciasABService;

    @GetMapping(value="/ajustebajo/")
    @Operation(summary="Devuelve un listado con todas las incidencias registradas con ajuste bajo", description="No tiene parámetros de entrada, se puede enviar pagina y registros por página", tags = {"Incidencias Ajuste Bajo"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Se encontraron incidencias"),
            @ApiResponse(responseCode="404", description="No se encontraron incidencias en BD")
    })
    public @ResponseBody Map<String, Object> getAllIncidenciasAjusteBajo( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size ) {

        List<TrazaAjusteBajo> trazaAjusteBajos = new ArrayList<TrazaAjusteBajo>();
        Pageable paging = PageRequest.of(page, size);
        Page<TrazaAjusteBajo> pageIncidencia;
        pageIncidencia = incidenciasABService.getAllIncidenciasAjusteBajo(paging);
        trazaAjusteBajos = pageIncidencia.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("incidencias", trazaAjusteBajos);
        response.put("currentPage", pageIncidencia.getNumber());
        response.put("totalItems", pageIncidencia.getTotalElements());
        response.put("totalPages", pageIncidencia.getTotalPages());

        return response;
    }

    @PostMapping(value="/ajustebajo/", produces= "application/json")
    @Operation(summary="Dar de alta una nueva incidencia", description="Permite agregar un incidencia manualmente", tags = {"Incidencias Ajuste Bajo"})
    public @ResponseBody void addIncidenciaAjusteBajo(TrazaAjusteBajo trazaAjusteBajo){
        incidenciasABService.addIncidenciaAjusteBajo(trazaAjusteBajo);

    }

    @GetMapping(value="/ajustebajo/{idIncidencia}")
    @Operation(summary="Obtener una indicencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias Ajuste Bajo"})
    public @ResponseBody Optional<TrazaAjusteBajo> getIncidenciaAjusteBajoById(@Parameter(description="id de la incidencia") @PathVariable("idIncidencia") Long id){
        return incidenciasABService.getIncidenciaAjusteBajoById(id);

    }

    @DeleteMapping(path="/ajustebajo/{id}", produces="application/json")
    @Operation(summary="Borrar una incidencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias Ajuste Bajo"})
    public @ResponseBody void delIncidenciaAjusteBajo(@Parameter(description="id de la incidencia") Long id) {
        incidenciasABService.delIncidenciaAjusteBajo(id);
    }

    @PutMapping(path="/ajustebajo/", produces="application/json")
    @Operation(summary="Modificar una incidencia una incidencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias Ajuste Bajo"})
    public @ResponseBody void UpdIncidenciAjusteBajo(TrazaAjusteBajo trazaAjusteBajo) {
        incidenciasABService.updIncidenciaAjusteBajo(trazaAjusteBajo);
    }
}
