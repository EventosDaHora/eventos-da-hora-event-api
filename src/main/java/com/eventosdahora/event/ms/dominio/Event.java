package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.time.LocalDate;

@Log
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event extends PanacheEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_event", length = 19)
    public Long id;

    @JoinColumn(name = "id_category")
    @ManyToOne(fetch = FetchType.LAZY)
    public Category idCategory;

    @JoinColumn(name = "id_status_event")
    @ManyToOne(fetch = FetchType.LAZY)
    public StatusEvent idStatus;

    @Column(name = "nm_event")
    public String name;

    @Column(name = "dt_event")
    public LocalDate date;

    @Column(name = "ds_event")
    public String description;


}
