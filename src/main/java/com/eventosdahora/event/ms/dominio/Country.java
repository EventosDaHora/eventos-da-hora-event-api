package com.eventosdahora.event.ms.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_country")
public class Country{
    
    @Id
    @SequenceGenerator(name = "seq_country", sequenceName = "seq_country", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_country")
    @Column(name = "id_country")
    public Long id;

    @Column(name = "ds_country")
    public String description;
}
