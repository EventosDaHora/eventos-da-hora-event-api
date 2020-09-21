package com.eventosdahora.event.ms.dominio;

import com.eventosdahora.event.ms.kafka.OrderState;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatusEvent extends PanacheEntity {

    @Column(name = "id_status_event")
    public Long id;

    @Column(name = "ds_status_event")
    @Enumerated(EnumType.STRING)
    public OrderState orderState;

}
