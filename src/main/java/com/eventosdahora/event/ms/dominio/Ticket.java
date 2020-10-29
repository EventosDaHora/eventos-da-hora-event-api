package com.eventosdahora.event.ms.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static io.quarkus.panache.common.Parameters.with;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket extends PanacheEntity {

    @Column(name = "id_ticket")
    public Long id;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section")
    public Section section;

    @Column(name = "qt_ticket_initial")
    public Long qtdInicial;
    
    @OneToMany(targetEntity = TicketReserved.class, mappedBy = "ticket", cascade = CascadeType.PERSIST)
    public List<TicketReserved> ticketReserved;
    
}
