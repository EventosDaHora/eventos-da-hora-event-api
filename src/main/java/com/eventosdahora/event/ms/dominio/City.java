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
@Table(name="tb_city")
public class City {
    
    @Id
    @SequenceGenerator(name = "seq_city", sequenceName = "seq_city", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_city")
    @Column(name = "id_city")
    public Long id;

    @Column(name = "ds_city")
    public String description;
}
