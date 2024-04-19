package valerio.U5W3D5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.U5W3D5.entity.User;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    boolean existsByEMail(String email);

    Optional<User> findByEMail(String email);
}
