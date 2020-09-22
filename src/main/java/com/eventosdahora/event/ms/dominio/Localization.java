package com.eventosdahora.event.ms.dominio;

import javax.persistence.*;

@Embeddable
public class Localization {

    @Column(name = "nm_localization")
    public String localization;

    @Column(name = "cep", length = 10, nullable = false)
    public String cep;

    @Column(name = "ds_address", nullable = false)
    public String address;

    @Column(name = "ds_complemento", nullable = false)
    public String complemento;

    @Column(name = "nu_address", length = 10, nullable = false)
    public String numero;

    @JoinColumn(name = "id_country")
    @ManyToOne
    public Country country;

    @JoinColumn(name = "id_city")
    @ManyToOne(fetch = FetchType.LAZY)
    public City city;
}
