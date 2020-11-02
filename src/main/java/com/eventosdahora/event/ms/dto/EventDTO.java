package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.dominio.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private Long idCategory;

    private String name;

    private LocalDateTime date;

    private String description;

    private LocalizationDTO localization;

    private List<SectionDTO> sections = new ArrayList<>();

    private List<ImageEventDTO> images = new ArrayList<>();

    public Event toEntity() {
        return Event.builder()
                .name(name)
                .date(date)
                .description(description)
                .localization(localization.toEntity())
                .build();
    }

}
