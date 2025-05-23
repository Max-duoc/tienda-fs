package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Producto;
import com.tienda_fs.tienda_fs.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.buscarPorId(id);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.obtenerTodos();
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> actualizarProducto(Long id, Producto producto) {
        return productoRepository.buscarPorId(id).map(existing -> {
            existing.setNombre(producto.getNombre());
            existing.setPrecio(producto.getPrecio());
            existing.setDescripcion(producto.getDescripcion());
            existing.setSku(producto.getSku());
            existing.setStock(producto.getStock());
            return productoRepository.save(existing);
        });
    }

    @Override
    public boolean eliminarProducto(Long id) {
        return productoRepository.buscarPorId(id).map(producto -> {
            productoRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
