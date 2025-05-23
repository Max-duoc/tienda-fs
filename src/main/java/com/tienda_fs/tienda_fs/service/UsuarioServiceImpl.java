package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Usuario;
import com.tienda_fs.tienda_fs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> BuscarPorId(Long id){
        return usuarioRepository.BuscarPorId(id);
    }

    @Override
    public List<Usuario> BuscarTodos(){
        return usuarioRepository.BuscarTodos();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> actualizarUsuario(Long id,Usuario usuario){
        return usuarioRepository.BuscarPorId(id).map(existing -> {
            existing.setNombre(usuario.getNombre());
            existing.setApellido(usuario.getApellido());
            existing.setEmail(usuario.getEmail());
            existing.setRol(usuario.getRol());
            existing.setContraseña(usuario.getContraseña());
            return usuarioRepository.save(existing);
        });
    }

    @Override
    public boolean eliminarUsuario(Long id){
        return usuarioRepository.BuscarPorId(id).map(usuario -> {
            usuarioRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
