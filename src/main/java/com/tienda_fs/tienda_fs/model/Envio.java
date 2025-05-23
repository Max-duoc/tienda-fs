package com.tienda_fs.tienda_fs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDate fechaEnvio;

    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonIgnoreProperties("envio")
    private Pedido pedido;
}
