package pl.coderslab.entity;
import jakarta.persistence.*;
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

    //    @NotBlank
    private String firstName;

    //    @NotBlank
    private String lastName;

    private String companyName;

    //    @Email
    private String email;


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
    @OneToMany(mappedBy = "designer")
    @ToString.Exclude
    private List<Customer> customer = new ArrayList<>();
    @OneToOne
    private User user;

}
