package com.eventosdahora.event.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SectionSugestionDTO {

    private String sugestion;

    @Getter
    @AllArgsConstructor
    public enum SectionSugestionEnum {
        PISTA("Pista"), CAMAROTE("Camarote"), VIP("VIP");

        private String section;
    }
}
