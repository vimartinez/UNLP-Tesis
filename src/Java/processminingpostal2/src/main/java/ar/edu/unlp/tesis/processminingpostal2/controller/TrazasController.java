package ar.edu.unlp.tesis.processminingpostal2.controller;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.unlp.tesis.processminingpostal2.model.Traza;
import ar.edu.unlp.tesis.processminingpostal2.service.TrazasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/trazas")
@CrossOrigin
public class TrazasController {
	
    @Autowired
    TrazasService trazasService;

    @PostMapping(value="/", produces= "application/json")
    @Operation(summary="Dar de alta una nueva traza", description="Permite agregar un traza manualmente", tags = {"Trazas"})
    public @ResponseBody void addTraza(Traza traza){
        trazasService.addTraza(traza);

    }

    @GetMapping(value="/{idTraza}")
    @Operation(summary="Obtener una indicencia de la base", description="Se debe enviar el id de la traza", tags = {"Trazas"})
    public @ResponseBody List<Traza> getTrazaById(@Parameter(description="id de la traza") @PathVariable("idTraza") Long id){
        return trazasService.getTrazaById(id);

    }

    @DeleteMapping(path="/{id}", produces="application/json")
    @Operation(summary="Borrar una traza de la base", description="Se debe enviar el id de la traza", tags = {"Trazas"})
    public @ResponseBody void delTraza(@Parameter(description="id de la traza") Long id) {
        trazasService.delTraza(id);
    }

    @PutMapping(path="/", produces="application/json")
    @Operation(summary="Modificar una traza una traza de la base", description="Se debe enviar objeto traza", tags = {"Trazas"})
    public @ResponseBody void UpdTraza(Traza traza) {
        trazasService.updTraza(traza);
    }

    @GetMapping(value="fecha/{fecha}")
    @Operation(summary="Obtener un listado de trazas según fecha", description="Se debe enviar el la fecha", tags = {"Trazas"})
    public @ResponseBody List<Traza> getAllTrazasByYear(@Parameter(description="Fecha de ingreso de la traza") Integer fecha){
        return trazasService.getAllTrazasByDate(fecha);

    }

    @GetMapping(value="/")
    @Operation(summary="Devuelve un listado con todas las trazas registradas", description="No tiene parámetros de entrada", tags = {"Trazas"})
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Se encontraron trazas"),
            @ApiResponse(responseCode="404", description="No se encontraron trazas en BD")
    })
    public ResponseEntity<Map<String, Object>> getAllTrazas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {
            List<Traza> trazas = new ArrayList<Traza>();
            Pageable paging = PageRequest.of(page, size);
            Page<Traza> pageTtraza;
            pageTtraza = trazasService.findAll(paging);
            trazas = pageTtraza.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("trazas", trazas);
            response.put("currentPage", pageTtraza.getNumber());
            response.put("totalItems", pageTtraza.getTotalElements());
            response.put("totalPages", pageTtraza.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
