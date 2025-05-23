package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Producto;
import com.tienda_fs.tienda_fs.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
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
    void testObtenerProductoPorIdExistente() {
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoRepository.buscarPorId(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.obtenerProductoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void testObtenerProductoPorIdInexistente() {
        when(productoRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        Optional<Producto> resultado = productoService.obtenerProductoPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testObtenerTodos() {
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        when(productoRepository.obtenerTodos()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> resultado = productoService.obtenerTodos();

        assertEquals(2, resultado.size());
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
    void testActualizarProductoExistente() {
        Producto original = new Producto();
        original.setId(1L);
        original.setNombre("Viejo");

        Producto actualizado = new Producto();
        actualizado.setNombre("Nuevo");
        actualizado.setPrecio(100.0);
        actualizado.setDescripcion("Desc");
        actualizado.setSku("ABC123");
        actualizado.setStock(5);

        when(productoRepository.buscarPorId(1L)).thenReturn(Optional.of(original));
        when(productoRepository.save(any(Producto.class))).thenReturn(original);

        Optional<Producto> resultado = productoService.actualizarProducto(1L, actualizado);

        assertTrue(resultado.isPresent());
        assertEquals("Nuevo", resultado.get().getNombre());
        assertEquals(100.0, resultado.get().getPrecio());
    }

    @Test
    void testActualizarProductoInexistente() {
        when(productoRepository.buscarPorId(999L)).thenReturn(Optional.empty());

        Optional<Producto> resultado = productoService.actualizarProducto(999L, new Producto());

        assertFalse(resultado.isPresent());
    }

    @Test
    void testEliminarProductoExistente() {
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoRepository.buscarPorId(1L)).thenReturn(Optional.of(producto));

        boolean resultado = productoService.eliminarProducto(1L);

        assertTrue(resultado);
        verify(productoRepository).deleteById(1L);
    }

    @Test
    void testEliminarProductoInexistente() {
        when(productoRepository.buscarPorId(999L)).thenReturn(Optional.empty());

        boolean resultado = productoService.eliminarProducto(999L);

        assertFalse(resultado);
        verify(productoRepository, never()).deleteById(any());
    }
}
