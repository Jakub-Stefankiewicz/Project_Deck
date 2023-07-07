package pl.coderslab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "designer")
@Data

public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String companyName;

    @Email
    private String email;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    private int postalCode;

    @NotBlank
    private String houseNumber;

    private int phone;

    private LocalDate added;

    private boolean active;

    @OneToMany(mappedBy = "designer")
    @ToString.Exclude
    private List<Customer> customer = new ArrayList<>();
    @OneToOne
    private User user;

}
