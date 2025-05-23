package com.tienda_fs.tienda_fs.controller;


import com.tienda_fs.tienda_fs.model.Reseña;
import com.tienda_fs.tienda_fs.service.ReseñaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ReseñaController {

    private final ReseñaService reseñaService;

    public ReseñaController(ReseñaService reseñaService){
        this.reseñaService = reseñaService;
    }

    @GetMapping
    public ResponseEntity<List<Reseña>> obtenerTodos(){
        List<Reseña> reseñas = reseñaService.obtenerTodos();
        return ResponseEntity.ok(reseñas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reseña> obtenerPorId(@PathVariable Long id){
        return reseñaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public List<Reseña>  obtenerTodosPorProducto(@PathVariable Long productoId){
        return reseñaService.obtenerPorProducto(productoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reseña>  obtenerTodosPorUsuario(@PathVariable Long usuarioId){
        return reseñaService.obtenerPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Reseña> crearReseña(@RequestBody Reseña reseña) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reseñaService.crearReseña(reseña));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reseña> actualizarReseña(@PathVariable Long id, @RequestBody Reseña reseña) {
        return reseñaService.actualizarReseña(id, reseña)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reseña> eliminarReseña(@PathVariable Long id){
        return reseñaService.eliminarReseña(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


}
