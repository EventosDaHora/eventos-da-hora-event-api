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
    public String localization;

    @Column(name = "cep", length = 10, nullable = false)
    public String cep;

    @Column(name = "ds_address", nullable = false)
    public String address;

    @Column(name = "ds_complemento", nullable = false)
    public String complement;

    @Column(name = "nu_address", length = 10, nullable = false)
    public String number;

    @JoinColumn(name = "id_country")
    @ManyToOne
    public Country country;

    @JoinColumn(name = "id_city")
    @ManyToOne(fetch = FetchType.LAZY)
    public City city;
}
