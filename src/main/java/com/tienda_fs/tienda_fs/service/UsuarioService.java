package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> BuscarPorId(Long id);
    List<Usuario> BuscarTodos();
    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> actualizarUsuario(Long id,Usuario usuario);
    boolean eliminarUsuario(Long id);
}
