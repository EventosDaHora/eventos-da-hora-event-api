package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends PanacheEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_ticket")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section")
    public Section section;

    @Column(name = "qt_ticket_initial")
    public Long qtdInicial;
}
