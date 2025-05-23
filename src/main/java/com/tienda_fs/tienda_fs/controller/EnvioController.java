package com.tienda_fs.tienda_fs.controller;

import com.tienda_fs.tienda_fs.model.Envio;
import com.tienda_fs.tienda_fs.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    @Autowired
    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public List<Envio> obtenerTodos(){
        return envioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable Long id){
        return envioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public List<Envio> obtenerPorPedidoId(@PathVariable Long pedidoId){
        return envioService.obtenerPorPedidoId(pedidoId);
    }

    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio){
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.crearEnvio(envio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Long id, @RequestBody Envio envio){
        return envioService.actualizarEnvio(id, envio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Envio> eliminarEnvio(@PathVariable Long id){
        return envioService.eliminarEnvio(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


}
