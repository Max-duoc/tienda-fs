package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Optional<Producto> obtenerProductoPorId(Long id);

    List<Producto> obtenerTodos();

    Producto crearProducto(Producto producto);

    Optional<Producto> actualizarProducto(Long id, Producto producto);

    boolean eliminarProducto(Long id);
}
