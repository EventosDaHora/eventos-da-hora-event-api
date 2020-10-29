package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event extends PanacheEntity {

    @Column(name = "id_event", length = 19)
    public Long id;

    @JoinColumn(name = "id_category")
    @ManyToOne(fetch = FetchType.LAZY)
    public Category categoryId;

    @JoinColumn(name = "id_status_event")
    @ManyToOne(fetch = FetchType.LAZY)
    public StatusEvent statusId;

    @Column(name = "nm_event")
    public String name;

    @Column(name = "dt_event")
    public LocalDate date;

    @Column(name = "ds_event")
    public String description;

    @Embedded
    public Localization localization;
    
    @OneToMany(targetEntity = Section.class, mappedBy = "event", cascade = CascadeType.PERSIST)
    public List<Section> sections;
    
    @OneToMany(targetEntity = ImageEvent.class, mappedBy = "event", cascade = CascadeType.PERSIST)
    public List<ImageEvent> images;
    
}
