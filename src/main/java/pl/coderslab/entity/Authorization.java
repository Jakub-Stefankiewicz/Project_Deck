package pl.coderslab.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;


@Entity
@Table(name = "authorization")
@Data
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate created;
    @ManyToOne
    private Designer designer;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne
    private Offer offer;
    private boolean accepted;

}
