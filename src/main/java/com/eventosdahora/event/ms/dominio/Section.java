package com.eventosdahora.event.ms.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section extends PanacheEntity {

    @Column(name = "id_section")
    public Long id;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event")
    public Event event;

    @Column(name = "nm_section")
    public String name;

    @Column(name = "ds_section", length = 500)
    public String description;

    @Column(name = "vl_amount")
    public BigDecimal ammount;

    public String metadata;
    
    @OneToMany(targetEntity = Ticket.class, mappedBy = "section", cascade = CascadeType.PERSIST)
    public List<Ticket> tickets;
    
    
    public static List<Section> findSectionsByEventId(Long eventId){
        return find("event.id", eventId).list();
    }
}
