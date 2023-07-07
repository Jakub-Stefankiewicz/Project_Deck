package pl.coderslab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotNull
    private int postalCode;

    @NotBlank
    private String houseNumber;

    @NotNull
    private int phone;


    private LocalDate added;

    private boolean active;

    @ManyToOne
    private Designer designer;
    @OneToOne
    private Offer offer;
    @OneToOne(cascade = CascadeType.ALL)
    private Deal deal;
    @OneToOne(cascade = CascadeType.ALL)
    private Authorization authorization;
    @OneToOne
    private User user;

}
