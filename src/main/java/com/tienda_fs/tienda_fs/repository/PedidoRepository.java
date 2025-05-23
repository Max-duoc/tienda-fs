package com.tienda_fs.tienda_fs.repository;

import com.tienda_fs.tienda_fs.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p")
    List<Pedido> obtenerTodos();

    @Query("SELECT p FROM Pedido p WHERE p.id= :id")
    Optional<Pedido> obtenerPorId(@Param("id") Long id);

    @Query("SELECT p FROM Pedido p WHERE p.usuario.id = :usuarioId")
    List<Pedido> obtenerPorUsuarioId(@Param("usuarioId") Long usuarioId);
}
