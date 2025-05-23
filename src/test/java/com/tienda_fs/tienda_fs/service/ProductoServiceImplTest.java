package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Producto;
import com.tienda_fs.tienda_fs.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceImplTest {

    private ProductoRepository productoRepository;
    private ProductoServiceImpl productoService;

    @BeforeEach
    void setUp() {
        productoRepository = mock(ProductoRepository.class);
        productoService = new ProductoServiceImpl(productoRepository);
    }

    @Test
    void testObtenerProductoPorId() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Laptop");

        when(productoRepository.buscarPorId(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.obtenerProductoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Laptop", resultado.get().getNombre());
    }

    @Test
    void testCrearProducto() {
        Producto producto = new Producto();
        producto.setNombre("Mouse");

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals("Mouse", resultado.getNombre());
    }

    @Test
    void testEliminarProductoExistente() {
        Producto producto = new Producto();
        producto.setId(2L);

        when(productoRepository.buscarPorId(2L)).thenReturn(Optional.of(producto));

        boolean eliminado = productoService.eliminarProducto(2L);

        assertTrue(eliminado);
        verify(productoRepository).deleteById(2L);
    }

    @Test
    void testEliminarProductoInexistente() {
        when(productoRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        boolean eliminado = productoService.eliminarProducto(99L);

        assertFalse(eliminado);
        verify(productoRepository, never()).deleteById(anyLong());
    }
}