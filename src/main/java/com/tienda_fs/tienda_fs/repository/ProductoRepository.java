package com.tienda_fs.tienda_fs.repository;

import com.tienda_fs.tienda_fs.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.id = :id")
    Optional<Producto> buscarPorId(@Param("id") Long id);

    @Query("SELECT p FROM Producto p")
    List<Producto> obtenerTodos();

}
