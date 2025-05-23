package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Pedido;
import com.tienda_fs.tienda_fs.model.Usuario;
import com.tienda_fs.tienda_fs.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private Pedido pedido;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);

        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setUsuario(usuario);
        pedido.setPrecio(100.0);
        pedido.setProductos(new ArrayList<>());
    }

    @Test
    void testObtenerPorId() {
        when(pedidoRepository.obtenerPorId(1L)).thenReturn(Optional.of(pedido));

        Optional<Pedido> result = pedidoService.obtenerPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(pedidoRepository).obtenerPorId(1L);
    }

    @Test
    void testObtenerTodos() {
        List<Pedido> pedidos = List.of(pedido);
        when(pedidoRepository.obtenerTodos()).thenReturn(pedidos);

        List<Pedido> result = pedidoService.obtenerTodos();

        assertEquals(1, result.size());
        verify(pedidoRepository).obtenerTodos();
    }

    @Test
    void testObtenerPorUsuarioId() {
        List<Pedido> pedidos = List.of(pedido);
        when(pedidoRepository.obtenerPorUsuarioId(1L)).thenReturn(pedidos);

        List<Pedido> result = pedidoService.obtenerPorUsuarioId(1L);

        assertEquals(1, result.size());
        verify(pedidoRepository).obtenerPorUsuarioId(1L);
    }

    @Test
    void testCrearPedido() {
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido result = pedidoService.crearPedido(pedido);

        assertNotNull(result);
        assertEquals(pedido.getId(), result.getId());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void testActualizarPedido() {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setUsuario(usuario);
        nuevoPedido.setPrecio(150.0);
        nuevoPedido.setProductos(new ArrayList<>());

        when(pedidoRepository.obtenerPorId(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Optional<Pedido> result = pedidoService.actualizarPedido(1L, nuevoPedido);

        assertTrue(result.isPresent());
        assertEquals(150.0, result.get().getPrecio());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void testEliminarPedido() {
        when(pedidoRepository.obtenerPorId(1L)).thenReturn(Optional.of(pedido));

        boolean result = pedidoService.eliminarPedido(1L);

        assertTrue(result);
        verify(pedidoRepository).deleteById(1L);
    }

    @Test
    void testEliminarPedidoNoEncontrado() {
        when(pedidoRepository.obtenerPorId(99L)).thenReturn(Optional.empty());

        boolean result = pedidoService.eliminarPedido(99L);

        assertFalse(result);
        verify(pedidoRepository, never()).deleteById(anyLong());
    }
}
