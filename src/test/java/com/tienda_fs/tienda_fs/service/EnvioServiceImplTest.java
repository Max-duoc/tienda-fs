package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Envio;
import com.tienda_fs.tienda_fs.repository.EnvioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnvioServiceImplTest {

    private EnvioRepository envioRepository;
    private EnvioServiceImpl envioService;

    @BeforeEach
    void setUp() {
        envioRepository = mock(EnvioRepository.class);
        envioService = new EnvioServiceImpl(envioRepository);
    }

    @Test
    void testObtenerTodos() {
        Envio e1 = new Envio();
        Envio e2 = new Envio();
        when(envioRepository.obtenerTodos()).thenReturn(Arrays.asList(e1, e2));

        List<Envio> resultado = envioService.obtenerTodos();

        assertEquals(2, resultado.size());
    }

    @Test
    void testObtenerPorIdExistente() {
        Envio envio = new Envio();
        envio.setId(1L);
        when(envioRepository.buscarPorId(1L)).thenReturn(Optional.of(envio));

        Optional<Envio> resultado = envioService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void testObtenerPorIdInexistente() {
        when(envioRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        Optional<Envio> resultado = envioService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testObtenerPorPedidoId() {
        Envio envio = new Envio();
        when(envioRepository.buscarPorPedidoId(5L)).thenReturn(List.of(envio));

        List<Envio> resultado = envioService.obtenerPorPedidoId(5L);

        assertEquals(1, resultado.size());
    }

    @Test
    void testCrearEnvio() {
        Envio envio = new Envio();
        envio.setDireccion("Calle Falsa 123");
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio resultado = envioService.crearEnvio(envio);

        assertNotNull(resultado);
        assertEquals("Calle Falsa 123", resultado.getDireccion());
    }

    @Test
    void testActualizarEnvioExistente() {
        Envio original = new Envio();
        original.setId(1L);
        original.setDireccion("Antigua");
        original.setEstado("Pendiente");

        Envio actualizado = new Envio();
        actualizado.setDireccion("Nueva");
        actualizado.setEstado("Entregado");

        when(envioRepository.buscarPorId(1L)).thenReturn(Optional.of(original));
        when(envioRepository.save(any(Envio.class))).thenReturn(original);

        Optional<Envio> resultado = envioService.actualizarEnvio(1L, actualizado);

        assertTrue(resultado.isPresent());
        assertEquals("Nueva", resultado.get().getDireccion());
        assertEquals("Entregado", resultado.get().getEstado());
    }

    @Test
    void testActualizarEnvioInexistente() {
        Envio envio = new Envio();
        when(envioRepository.buscarPorId(999L)).thenReturn(Optional.empty());

        Optional<Envio> resultado = envioService.actualizarEnvio(999L, envio);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testEliminarEnvioExistente() {
        Envio envio = new Envio();
        envio.setId(1L);
        when(envioRepository.buscarPorId(1L)).thenReturn(Optional.of(envio));

        boolean resultado = envioService.eliminarEnvio(1L);

        assertTrue(resultado);
        verify(envioRepository).delete(envio);
    }

    @Test
    void testEliminarEnvioInexistente() {
        when(envioRepository.buscarPorId(999L)).thenReturn(Optional.empty());

        boolean resultado = envioService.eliminarEnvio(999L);

        assertFalse(resultado);
        verify(envioRepository, never()).delete(any());
    }
}
