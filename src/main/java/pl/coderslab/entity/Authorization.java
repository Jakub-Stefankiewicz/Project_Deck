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
    @OneToOne(cascade = {CascadeType.ALL})
    private Designer designer;
    @OneToOne(cascade = {CascadeType.ALL})
    private Customer customer;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Offer offer;
    private boolean accepted;
}
