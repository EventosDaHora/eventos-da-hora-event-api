package com.eventosdahora.event.ms.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    @Column(name = "nm_localization")
    private String localization;

    @Column(name = "cep")
    private String cep;

    @Column(name = "ds_address")
    private String address;

    @Column(name = "ds_complemento")
    private String complement;

    @Column(name = "nu_address")
    private String number;

    @JoinColumn(name = "id_country")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Country country;

    @JoinColumn(name = "id_city")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private City city;
}
