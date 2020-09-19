package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class City extends PanacheEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_city")
    public long id;

    @Column(name = "ds_city")
    public String descricao;
}
