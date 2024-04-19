package valerio.U5W3D5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.U5W3D5.entity.Event;

import java.util.Optional;

public interface EventDAO extends JpaRepository<Event, Long> {
    boolean existsByName(String name);

    Optional<Event> findByName(String name);
}
