package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

import static io.quarkus.panache.common.Parameters.with;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket extends PanacheEntity {

    @Column(name = "id_ticket")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section")
    public Section section;

    @Column(name = "qt_ticket_initial")
    public Long qtdInicial;
    
}
