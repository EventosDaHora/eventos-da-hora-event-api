package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City extends PanacheEntity {

    @Column(name = "id_city")
    public long id;

    @Column(name = "ds_city")
    public String descricao;
}
