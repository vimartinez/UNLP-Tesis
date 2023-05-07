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

import ar.edu.unlp.tesis.processminingpostal2.model.Traza;
import ar.edu.unlp.tesis.processminingpostal2.service.TrazasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/trazas")
public class TrazasController {
	
    @Autowired
    TrazasService trazasService;

    @GetMapping(value="/")
    @Operation(summary="Devuelve un listado con todas las trazas registradas", description="No tiene parámetros de entrada", tags = {"Trazas"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Se encontraron trazas"),
            @ApiResponse(responseCode="404", description="No se encontraron trazas en BD")
    })
    public @ResponseBody List<Traza> getAllTrazas(){
        return trazasService.getAllTrazas();

    }

    @PostMapping(value="/", produces= "application/json")
    @Operation(summary="Dar de alta una nueva traza", description="Permite agregar un traza manualmente", tags = {"Trazas"})
    public @ResponseBody void addTraza(Traza traza){
        trazasService.addTraza(traza);

    }

    @GetMapping(value="/{idTraza}")
    @Operation(summary="Obtener una indicencia de la base", description="Se debe enviar el id de la traza", tags = {"Trazas"})
    public @ResponseBody Optional<Traza> getTrazaById(@Parameter(description="id de la traza") @PathVariable("idTraza") Long id){
        return trazasService.getTrazaById(id);

    }

    @DeleteMapping(path="/{id}", produces="application/json")
    @Operation(summary="Borrar una traza de la base", description="Se debe enviar el id de la traza", tags = {"Trazas"})
    public @ResponseBody void delTraza(@Parameter(description="id de la traza") Long id) {
        trazasService.delTraza(id);
    }

    @PutMapping(path="/", produces="application/json")
    @Operation(summary="Modificar una traza una traza de la base", description="Se debe enviar el id de la traza", tags = {"Trazas"})
    public @ResponseBody void UpdTraza(Traza traza) {
        trazasService.updTraza(traza);
    }

    @GetMapping(value="year/{year}")
    @Operation(summary="Obtener un listado de trazas según el año de estreno", description="Se debe enviar el año de la traza", tags = {"Trazas"})
    public @ResponseBody List<Traza> getAllTrazasByYear(@Parameter(description="Año de estreno de la traza") Integer fecha){
        return trazasService.getAllTrazasByDate(fecha);

    }

}
