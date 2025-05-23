package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Envio;

import java.util.List;
import java.util.Optional;

public interface EnvioService {

    List<Envio> obtenerTodos();
    Optional<Envio> obtenerPorId(Long id);
    List<Envio> obtenerPorPedidoId(Long pedidoId);
    Envio crearEnvio(Envio envio);
    Optional<Envio> actualizarEnvio(Long id, Envio envio);
    boolean eliminarEnvio(Long id);



}
