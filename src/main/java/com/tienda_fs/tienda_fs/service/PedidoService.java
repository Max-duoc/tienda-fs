package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    Pedido crearPedido(Pedido pedido);
    Optional<Pedido> obtenerPorId(Long id);
    List<Pedido> obtenerTodos();
    List<Pedido> obtenerPorUsuarioId(Long usuarioId);
    Optional<Pedido> actualizarPedido(Long id, Pedido pedido);
    boolean eliminarPedido(Long id);




}
