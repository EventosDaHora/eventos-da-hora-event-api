package com.eventosdahora.event.ms.dominio;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_event")
public class Event {
    
    @Id
    @SequenceGenerator(name = "seq_event", sequenceName = "seq_event", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_event")
    @Column(name = "id_event", length = 19)
    private Long id;

    @JoinColumn(name = "id_category")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @JoinColumn(name = "id_status_event")
    @ManyToOne(fetch = FetchType.LAZY)
    private StatusEvent status;

    @Column(name = "nm_event")
    private String name;

    @Column(name = "dt_event")
    private LocalDateTime date;

    @Column(name = "ds_event")
    private String description;

    @Embedded
    private Localization localization;
    
    @OneToMany(targetEntity = Section.class, mappedBy = "event", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private List<Section> sections = new ArrayList<>();
    
    @OneToMany(targetEntity = ImageEvent.class, mappedBy = "event", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private List<ImageEvent> images = new ArrayList<>();

    public void addSection(Section section) {
        sections.add(section);
        section.setEvent(this);
    }

    public void removeSection(Section section) {
        sections.remove(section);
        section.setEvent(null);
    }

    public void addImage(ImageEvent image) {
        images.add(image);
        image.setEvent(this);
    }

    public void removeImage(ImageEvent image) {
        images.remove(image);
        image.setEvent(this);

    }
}
