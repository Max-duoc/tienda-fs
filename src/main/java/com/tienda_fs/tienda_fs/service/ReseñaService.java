package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Reseña;

import java.util.List;
import java.util.Optional;

public interface ReseñaService {
    Reseña crearReseña(Reseña reseña);
    Optional<Reseña> obtenerPorId(Long id);
    List<Reseña>  obtenerTodos();
    List<Reseña> obtenerPorProducto(Long productoId);
    List<Reseña> obtenerPorUsuario(Long usuarioId);
    Optional<Reseña> actualizarReseña(Long id, Reseña reseña);
    boolean eliminarReseña(Long id);
}
