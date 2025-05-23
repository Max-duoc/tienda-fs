package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Reseña;
import com.tienda_fs.tienda_fs.model.Usuario;
import com.tienda_fs.tienda_fs.model.Producto;
import com.tienda_fs.tienda_fs.repository.ReseñaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReseñaServiceImplTest {

    @Mock
    private ReseñaRepository reseñaRepository;

    @InjectMocks
    private ReseñaServiceImpl reseñaService;

    private Reseña reseña;
    private Usuario usuario;
    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);

        producto = new Producto();
        producto.setId(1L);

        reseña = new Reseña();
        reseña.setId(1L);
        reseña.setCalificacion(5);
        reseña.setComentario("Excelente");
        reseña.setUsuario(usuario);
        reseña.setProducto(producto);
    }

    @Test
    void testCrearReseña() {
        when(reseñaRepository.save(reseña)).thenReturn(reseña);

        Reseña result = reseñaService.crearReseña(reseña);

        assertNotNull(result);
        assertEquals(5, result.getCalificacion());
        verify(reseñaRepository).save(reseña);
    }

    @Test
    void testObtenerPorId() {
        when(reseñaRepository.buscarPorId(1L)).thenReturn(Optional.of(reseña));

        Optional<Reseña> result = reseñaService.obtenerPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Excelente", result.get().getComentario());
        verify(reseñaRepository).buscarPorId(1L);
    }

    @Test
    void testObtenerTodos() {
        List<Reseña> lista = List.of(reseña);
        when(reseñaRepository.obtenerTodos()).thenReturn(lista);

        List<Reseña> result = reseñaService.obtenerTodos();

        assertEquals(1, result.size());
        verify(reseñaRepository).obtenerTodos();
    }

    @Test
    void testObtenerPorProducto() {
        List<Reseña> lista = List.of(reseña);
        when(reseñaRepository.obtenerPorProducto(1L)).thenReturn(lista);

        List<Reseña> result = reseñaService.obtenerPorProducto(1L);

        assertEquals(1, result.size());
        verify(reseñaRepository).obtenerPorProducto(1L);
    }

    @Test
    void testObtenerPorUsuario() {
        List<Reseña> lista = List.of(reseña);
        when(reseñaRepository.obtenerPorUsuario(1L)).thenReturn(lista);

        List<Reseña> result = reseñaService.obtenerPorUsuario(1L);

        assertEquals(1, result.size());
        verify(reseñaRepository).obtenerPorUsuario(1L);
    }

    @Test
    void testActualizarReseña() {
        Reseña nueva = new Reseña();
        nueva.setCalificacion(4);
        nueva.setComentario("Muy buena");
        nueva.setUsuario(usuario);
        nueva.setProducto(producto);

        when(reseñaRepository.buscarPorId(1L)).thenReturn(Optional.of(reseña));
        when(reseñaRepository.save(any(Reseña.class))).thenReturn(reseña);

        Optional<Reseña> result = reseñaService.actualizarReseña(1L, nueva);

        assertTrue(result.isPresent());
        assertEquals("Muy buena", result.get().getComentario());
        assertEquals(4, result.get().getCalificacion());
        verify(reseñaRepository).save(reseña);
    }

    @Test
    void testEliminarReseñaExistente() {
        when(reseñaRepository.existsById(1L)).thenReturn(true);

        boolean result = reseñaService.eliminarReseña(1L);

        assertTrue(result);
        verify(reseñaRepository).deleteById(1L);
    }

    @Test
    void testEliminarReseñaNoExistente() {
        when(reseñaRepository.existsById(99L)).thenReturn(false);

        boolean result = reseñaService.eliminarReseña(99L);

        assertFalse(result);
        verify(reseñaRepository, never()).deleteById(anyLong());
    }
}
