package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category extends PanacheEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_category")
    public Long id;

    @Column(name = "nm_category", unique = true)
    public String name;

    @Column(name = "ds_categoria", nullable = false)
    public String categoria;
}
