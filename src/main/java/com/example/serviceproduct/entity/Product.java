package com.example.serviceproduct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor //Lombok Constructor
@NoArgsConstructor // Lombok Constructor Vacio
@Builder // Para poder Instanciarlo
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String status;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER Genera todas las categorias LAZY genera la que necesita
    @JoinColumn(name = "category_id")
    private Category category;

}
