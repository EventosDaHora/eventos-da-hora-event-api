package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Country extends PanacheEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_country")
    public Long id;

    @Column(name = "ds_country")
    public String descricao;
}
