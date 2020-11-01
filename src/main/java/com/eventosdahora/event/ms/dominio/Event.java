package com.eventosdahora.event.ms.dominio;

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
@Table(name="tb_event")
public class Event {
    
    @Id
    @SequenceGenerator(name = "seq_event", sequenceName = "seq_event", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_event")
    @Column(name = "id_event", length = 19)
    public Long id;

    @JoinColumn(name = "id_category")
    @ManyToOne(fetch = FetchType.LAZY)
    public Category category;

    @JoinColumn(name = "id_status_event")
    @ManyToOne(fetch = FetchType.LAZY)
    public StatusEvent status;

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
