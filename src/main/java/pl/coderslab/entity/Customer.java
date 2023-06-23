package pl.coderslab.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank
    private String username;

    //    @NotBlank
    private String firstName;

    //    @NotBlank
    private String lastName;

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



}
