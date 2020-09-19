package com.eventosdahora.event.ms.dominio;

import com.eventosdahora.event.ms.kafka.OrderState;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StatusEvent extends PanacheEntity {

    @Id
    @Column(name = "id_status_event")
    @GeneratedValue
    public Long id;

    @Column(name = "ds_status_event")
    @Enumerated(EnumType.STRING)
    public OrderState orderState;

}
