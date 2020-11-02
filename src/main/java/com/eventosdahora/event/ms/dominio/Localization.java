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

    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Column(name = "ds_address", nullable = false)
    private String address;

    @Column(name = "ds_complemento", nullable = false)
    private String complement;

    @Column(name = "nu_address", length = 10, nullable = false)
    private String number;

    @JoinColumn(name = "id_country")
    @ManyToOne
    private Country country;

    @JoinColumn(name = "id_city")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
}
