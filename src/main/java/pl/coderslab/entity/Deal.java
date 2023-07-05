package pl.coderslab.entity;
import jakarta.persistence.*;
import lombok.Data;
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
    private String notes;
    @ManyToOne
    private Designer designer;
    @OneToOne
    private Customer customer;
    private boolean accepted;
    @OneToOne(cascade = CascadeType.ALL)
    private Offer offer;

}
