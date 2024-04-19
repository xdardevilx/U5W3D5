package valerio.U5W3D5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import valerio.U5W3D5.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "utenti")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Role role;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<>();

    public User(String name, String surname, Role role, String email, String password, List<Event> events) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.events = events;
    }
}
