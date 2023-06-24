package pl.coderslab.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "authorization")
@Data
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate created;
    @OneToOne
    private Designer designer;
    @OneToOne
    private Customer customer;
    private boolean accepted;
}
