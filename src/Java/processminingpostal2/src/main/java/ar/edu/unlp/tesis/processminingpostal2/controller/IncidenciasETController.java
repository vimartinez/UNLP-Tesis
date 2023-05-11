package ar.edu.unlp.tesis.processminingpostal2.controller;

import ar.edu.unlp.tesis.processminingpostal2.model.TrazaExcesoTiempo;
import ar.edu.unlp.tesis.processminingpostal2.service.IncidenciasETService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/incidencias")
@CrossOrigin
public class IncidenciasETController {
	
	@Autowired
    IncidenciasETService incidenciasETService;

    @GetMapping(value="/excesotiempo/")
    @Operation(summary="Devuelve un listado con todas las incidencias registradas con ajuste bajo", description="No tiene parámetros de entrada, se puede enviar pagina y registros por página", tags = {"Incidencias Exceso de Tiempo"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Se encontraron incidencias"),
            @ApiResponse(responseCode="404", description="No se encontraron incidencias en BD")
    })
    public @ResponseBody Map<String, Object> getAllIncidenciasExcesoTiempo( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size ) {

        List<TrazaExcesoTiempo> trazaExcesoTiempos = new ArrayList<TrazaExcesoTiempo>();
        Pageable paging = PageRequest.of(page, size);
        Page<TrazaExcesoTiempo> pageIncidencia;
        pageIncidencia = incidenciasETService.getAllIncidenciasExcesoTiempo(paging);
        trazaExcesoTiempos = pageIncidencia.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("incidencias", trazaExcesoTiempos);
        response.put("currentPage", pageIncidencia.getNumber());
        response.put("totalItems", pageIncidencia.getTotalElements());
        response.put("totalPages", pageIncidencia.getTotalPages());

        return response;
    }

    @PostMapping(value="/excesotiempo/", produces= "application/json")
    @Operation(summary="Dar de alta una nueva incidencia", description="Permite agregar un incidencia manualmente", tags = {"Incidencias Exceso de Tiempo"})
    public @ResponseBody void addIncidenciaExcesoTiempo(TrazaExcesoTiempo trazaExcesoTiempo){
        incidenciasETService.addIncidenciaExcesoTiempo(trazaExcesoTiempo);

    }

    @GetMapping(value="/excesotiempo/{idIncidencia}")
    @Operation(summary="Obtener una indicencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias Exceso de Tiempo"})
    public @ResponseBody Optional<TrazaExcesoTiempo> getIncidenciaExcesoTiempoById(@Parameter(description="id de la incidencia") @PathVariable("idIncidencia") Long id){
        return incidenciasETService.getIncidenciaExcesoTiempoById(id);

    }

    @DeleteMapping(path="/excesotiempo/{id}", produces="application/json")
    @Operation(summary="Borrar una incidencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias Exceso de Tiempo"})
    public @ResponseBody void delIncidenciaExcesoTiempo(@Parameter(description="id de la incidencia") Long id) {
        incidenciasETService.delIncidenciaExcesoTiempo(id);
    }

    @PutMapping(path="/excesotiempo/", produces="application/json")
    @Operation(summary="Modificar una incidencia una incidencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias Exceso de Tiempo"})
    public @ResponseBody void UpdIncidenciExcesoTiempo(TrazaExcesoTiempo trazaExcesoTiempo) {
        incidenciasETService.updIncidenciaExcesoTiempo(trazaExcesoTiempo);
    }


}
