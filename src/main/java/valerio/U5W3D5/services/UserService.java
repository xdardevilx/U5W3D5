package valerio.U5W3D5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valerio.U5W3D5.entity.User;
import valerio.U5W3D5.enums.Role;
import valerio.U5W3D5.exceptions.BadRequestException;
import valerio.U5W3D5.exceptions.NotFoundException;
import valerio.U5W3D5.payloads.UserDTO;
import valerio.U5W3D5.payloads.UserResponseDTO;
import valerio.U5W3D5.repositories.UserDAO;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    public UserResponseDTO save(UserDTO newUser) throws BadRequestException {
        Optional<User> existingUser = userDAO.findByEMail(newUser.email());
        if (existingUser.isPresent()) {
            throw new BadRequestException("l'utente esiste già");
        }
        User user = new User();
        user.setName(newUser.name());
        user.setSurname(newUser.surname());
        user.setRole(Role.USER);
        user.setEmail(newUser.email());
        user.setPassword(bcrypt.encode(newUser.password()));
        return new UserResponseDTO(user.getEmail());
    }

    public Page<User> getUser(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.userDAO.findAll(pageable);
    }

    public User findById(long Userid) {
        return this.userDAO.findById(Userid).orElseThrow(() -> new NotFoundException(Userid));
    }

    public void findByAndDelete(long id) {
        User user = this.findById(id);
        this.userDAO.delete(user);
    }

    public User findByIdAndUpdate(long id, UserDTO newUser) {
        User user = this.findById(id);
        user.setName(newUser.name());
        user.setSurname(newUser.surname());
        user.setEmail(newUser.email());
        return this.userDAO.save(user);
    }


}
