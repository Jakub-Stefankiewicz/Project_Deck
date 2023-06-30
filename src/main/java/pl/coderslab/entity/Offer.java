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
    @OneToMany
    private List<Customer> customer;
    @OneToMany
    private List<Deal> deals;
    @OneToMany
    private List<Authorization> authorizations;
}
