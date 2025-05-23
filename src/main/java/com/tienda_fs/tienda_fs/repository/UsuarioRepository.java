package com.tienda_fs.tienda_fs.repository;

import com.tienda_fs.tienda_fs.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u")
    List<Usuario> BuscarTodos();

    @Query("SELECT u FROM Usuario u WHERE u.id= :id")
    Optional<Usuario> BuscarPorId(Long id);
}
