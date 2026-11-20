package com.techlab.articulo_api.controller;

import com.techlab.articulo_api.model.Articulo;
import com.techlab.articulo_api.service.ArticuloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {


    private final ArticuloServiceImpl articuloService;


    @Autowired
    public ArticuloController(ArticuloServiceImpl articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listarArticulos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo){
        return articuloService.guardarArticulo(articulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        if(articuloService.obtenerArticuloPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articuloService.actualizarArticulo(id, articulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if(articuloService.obtenerArticuloPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build();
    }

}
