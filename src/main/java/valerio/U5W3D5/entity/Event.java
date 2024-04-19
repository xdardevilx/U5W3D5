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
@Table(name = "eventi")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEvent;
    private String descriptionEvent;
    private String dateEvent;
    private int maxParticipants;

    @ManyToMany
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<User> participants = new ArrayList<>();

    public Event(String nameEvent, String descriptionEvent, String dateEvent, int maxParticipants, List<User> participants) {
        this.nameEvent = nameEvent;
        this.descriptionEvent = descriptionEvent;
        this.dateEvent = dateEvent;
        this.maxParticipants = maxParticipants;
        this.participants = participants;
    }
}
