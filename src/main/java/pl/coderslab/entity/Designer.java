package pl.coderslab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "designer")
@Data

public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank
    private String username;

    //    @NotBlank
    private String firstName;

    //    @NotBlank
    private String lastName;

    private String companyName;


    //    @Email
    private String email;

    //    @NotBlank
    private String password;

    //    @NotBlank
    private String city;

    //    @NotBlank
    private String street;

    //    @NotBlank
    private int postalCode;

    //    @NotBlank
    private String houseNumber;

    //    @NotBlank
    private int phone;

    //    @NotEmpty
    private LocalDate added;

    //    @NotEmpty
    private boolean active;

    //    @NotEmpty
    @OneToMany
    @ToString.Exclude
    private List<Customer> customers;

}
