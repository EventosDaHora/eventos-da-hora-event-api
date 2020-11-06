package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.dominio.City;
import com.eventosdahora.event.ms.dominio.Country;
import com.eventosdahora.event.ms.dominio.Localization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationDTO {

    private String localization;

    private String cep;

    private String address;

    private String complement;

    private String number;

    private String country;

    private String city;

    public Localization toEntity() {
        return Localization.builder()
                .country(new Country(null, country))
                .city(new City(null, city))
                .localization(localization)
                .cep(cep)
                .address(address)
                .complement(complement)
                .number(number)
                .build();
    }
}
