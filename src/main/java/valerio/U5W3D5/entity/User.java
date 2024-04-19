package valerio.U5W3D5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String email;
    private String password;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<>();

    public User(String name, String surname, String email, String password, List<Event> events) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.events = events;
    }
}
