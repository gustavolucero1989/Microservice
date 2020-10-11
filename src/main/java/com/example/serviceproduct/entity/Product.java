package com.example.serviceproduct.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    // EAGER Genera todas las categorias LAZY genera la que necesita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    //Ignoramos el error de hibernate
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

}
