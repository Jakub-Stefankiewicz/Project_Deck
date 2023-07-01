package pl.coderslab.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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
    @OneToOne
    private Customer customer;
    @OneToOne
    private Offer offer;
    private boolean accepted;

}
