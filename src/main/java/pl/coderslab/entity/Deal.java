package pl.coderslab.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "deal")
@Data

public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private LocalDate created;
    private double value;
    @NotBlank
    private String notes;
    @ManyToOne
    private Designer designer;
    @ToString.Exclude
    @OneToOne
    private Customer customer;
    private boolean accepted;
    @OneToOne(cascade = CascadeType.ALL)
    private Offer offer;

}
