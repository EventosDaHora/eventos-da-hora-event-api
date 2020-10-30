package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImageEvent extends PanacheEntity {

    @Column(name = "id_image_event")
    public Long id;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event")
    public Event event;
    
    @Column(name = "id_image")
    public Long imageId;

    @Column(name = "ds_image_type")
    public String imageType;
}
