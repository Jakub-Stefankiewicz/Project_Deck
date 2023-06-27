package pl.coderslab.entity;

import jakarta.persistence.*;
import lombok.Data;
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
    private String eventName;
    private LocalDate expiration;
    private boolean completed;
    private boolean endangered;
    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.ALL})
    private Tree tree;
    @ToString.Exclude
    @ManyToMany
    private List<Event> events;
}
