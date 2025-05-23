package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Usuario;
import com.tienda_fs.tienda_fs.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setEmail("juan@example.com");
        usuario.setRol("cliente");
        usuario.setContraseña("123456");
    }

    @Test
    void testBuscarPorId() {
        when(usuarioRepository.BuscarPorId(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.BuscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombre());
        verify(usuarioRepository).BuscarPorId(1L);
    }

    @Test
    void testBuscarTodos() {
        List<Usuario> lista = List.of(usuario);
        when(usuarioRepository.BuscarTodos()).thenReturn(lista);

        List<Usuario> result = usuarioService.BuscarTodos();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
        verify(usuarioRepository).BuscarTodos();
    }

    @Test
    void testCrearUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.crearUsuario(usuario);

        assertNotNull(result);
        assertEquals("juan@example.com", result.getEmail());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testActualizarUsuario() {
        Usuario nuevo = new Usuario();
        nuevo.setNombre("Carlos");
        nuevo.setApellido("Gómez");
        nuevo.setEmail("carlos@example.com");
        nuevo.setRol("admin");
        nuevo.setContraseña("nueva123");

        when(usuarioRepository.BuscarPorId(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Optional<Usuario> result = usuarioService.actualizarUsuario(1L, nuevo);

        assertTrue(result.isPresent());
        assertEquals("Carlos", result.get().getNombre());
        assertEquals("admin", result.get().getRol());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testEliminarUsuario() {
        when(usuarioRepository.BuscarPorId(1L)).thenReturn(Optional.of(usuario));

        boolean result = usuarioService.eliminarUsuario(1L);

        assertTrue(result);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void testEliminarUsuarioNoExistente() {
        when(usuarioRepository.BuscarPorId(99L)).thenReturn(Optional.empty());

        boolean result = usuarioService.eliminarUsuario(99L);

        assertFalse(result);
        verify(usuarioRepository, never()).deleteById(anyLong());
    }
}
