package com.tienda_fs.tienda_fs.repository;

import com.tienda_fs.tienda_fs.model.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReseñaRepository extends JpaRepository<Reseña,Long> {
    @Query("SELECT r FROM Reseña r")
    List<Reseña> obtenerTodos();

    @Query("SELECT r FROM Reseña r WHERE r.id= :id")
    Optional<Reseña> buscarPorId(@Param("id") Long id);

    @Query("SELECT r FROM Reseña r WHERE r.producto.id = :productoId")
    List<Reseña> obtenerPorProducto(@Param("productoId") Long productoId);

    @Query("SELECT r FROM Reseña r WHERE r.usuario.id = :usuarioId")
    List<Reseña> obtenerPorUsuario(@Param("usuarioId") Long usuarioId);
}