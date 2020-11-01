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
@Table(name = "tb_status_event")
public class StatusEvent{
    
    @Id
    @Column(name = "id_status_event")
    public Long id;

    @Column(name = "ds_status_event")
    @Enumerated(EnumType.STRING)
    public StatusEventEnum statusEvent;
    
}
