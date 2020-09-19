package com.eventosdahora.event.ms.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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

    @Column(name = "id_country", length = 19)
    public Long idCountry;

    @Column(name = "id_city", length = 19)
    public Long idCity;
}
