package ar.edu.unlp.tesis.processminingpostal2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlp.tesis.processminingpostal2.model.Incidencia;
import ar.edu.unlp.tesis.processminingpostal2.service.IncidenciasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/incidencias")
public class IncidenciasController {
	
	@Autowired
    IncidenciasService incidenciasService;

    @GetMapping(value="/")
    @Operation(summary="Devuelve un listado con todas las incidencias registradas", description="No tiene parámetros de entrada", tags = {"Incidencias"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Se encontraron incidencias"),
            @ApiResponse(responseCode="404", description="No se encontraron incidencias en BD")
    })
    public @ResponseBody List<Incidencia> getAllTrazas(){
        return incidenciasService.getAllIncidencias();

    }

    @PostMapping(value="/", produces= "application/json")
    @Operation(summary="Dar de alta una nueva incidencia", description="Permite agregar un incidencia manualmente", tags = {"Incidencias"})
    public @ResponseBody void addTraza(Incidencia incidencia){
        incidenciasService.addIncidencia(incidencia);

    }

    @GetMapping(value="/{idTraza}")
    @Operation(summary="Obtener una indicencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias"})
    public @ResponseBody Optional<Incidencia> getTrazaById(@Parameter(description="id de la incidencia") @PathVariable("idTraza") Long id){
        return incidenciasService.getIncidenciaById(id);

    }

    @DeleteMapping(path="/{id}", produces="application/json")
    @Operation(summary="Borrar una incidencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias"})
    public @ResponseBody void delTraza(@Parameter(description="id de la incidencia") Long id) {
        incidenciasService.delIncidencia(id);
    }

    @PutMapping(path="/", produces="application/json")
    @Operation(summary="Modificar una incidencia una incidencia de la base", description="Se debe enviar el id de la incidencia", tags = {"Incidencias"})
    public @ResponseBody void UpdTraza(Incidencia incidencia) {
        incidenciasService.updIncidencia(incidencia);
    }

    @GetMapping(value="year/{year}")
    @Operation(summary="Obtener un listado de incidencias según el año de estreno", description="Se debe enviar el año de la incidencia", tags = {"Incidencias"})
    public @ResponseBody List<Incidencia> getAllTrazasByYear(@Parameter(description="Año de estreno de la incidencia") Integer fecha){
        return incidenciasService.getAllIncidenciasByDate(fecha);

    }

}
