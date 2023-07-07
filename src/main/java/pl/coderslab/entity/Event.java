package pl.coderslab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "event")
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String eventName;
    private LocalDate expiration;
    private boolean completed;
    private boolean endangered;
    private boolean finalEvent;
    @ToString.Exclude
    @ManyToMany
    private List<Event> events;
    @ToString.Exclude
    @ManyToOne
    private Offer offer;
    private Long templateId;

}
