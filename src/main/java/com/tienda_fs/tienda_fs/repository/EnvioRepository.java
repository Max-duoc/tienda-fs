package com.tienda_fs.tienda_fs.repository;

import com.tienda_fs.tienda_fs.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    @Query("SELECT e FROM Envio e")
    List<Envio> obtenerTodos();

    @Query("SELECT e FROM Envio e WHERE e.id = :id")
    Optional<Envio> buscarPorId(@Param("id") Long id);

    @Query("SELECT e FROM Envio e WHERE e.pedido.id = :pedidoId")
    List<Envio> buscarPorPedidoId(@Param("pedidoId") Long pedidoId);
}
