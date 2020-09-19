package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TicketReserved extends PanacheEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_ticket_reserved")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket")
    public Ticket ticket;

    @Column(name = "qtd_tickets_reserved")
    public Long qtdTicketsReserved;

    @Column(name = "dt_expired")
    public LocalDate dataExpiracao;

    @Column(name = "id_order")
    public Long idOrder;

    @Column(name = "id_confirmed")
    public Boolean confirmed;

}
