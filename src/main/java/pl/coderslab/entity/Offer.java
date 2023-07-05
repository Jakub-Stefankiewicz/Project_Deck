package pl.coderslab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "offers")
@Getter
@Setter
@ToString
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectType;
    private double price;
    @ManyToOne
    private Designer designer;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "offer")
    private List<Event> events;
    /**
     * Is offer a template of offers? Template offers are created to define schema and
     * to be copied to new customer.
     */
    private Boolean template;
    @OneToOne
    private Customer customer;
}
