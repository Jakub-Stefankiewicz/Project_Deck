package pl.coderslab.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tree")
@Data
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String treeName;
    @OneToMany(mappedBy = "tree")
    private List<Event> events;
}
