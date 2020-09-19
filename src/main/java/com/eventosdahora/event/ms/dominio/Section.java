package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Section extends PanacheEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_section")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event")
    public Event event;

    @Column(name = "nm_section")
    public String nome;

    @Column(name = "ds_section", length = 500)
    public String descricao;

    @Column(name = "vi_amount")
    public BigDecimal ammount;

    public String metadata;

}
