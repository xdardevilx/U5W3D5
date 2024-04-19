package valerio.U5W3D5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.U5W3D5.entity.User;
import valerio.U5W3D5.enums.Role;
import valerio.U5W3D5.exceptions.BadRequestException;
import valerio.U5W3D5.exceptions.CorrectDeleteUser;
import valerio.U5W3D5.exceptions.NotFoundException;
import valerio.U5W3D5.payloads.UserDTO;
import valerio.U5W3D5.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO user(@RequestBody @Validated UserDTO body, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new BadRequestException(result.getAllErrors());
        }
        User user = new User();
        user.setName(body.name());
        user.setSurname(body.surname());
        user.setRole(Role.valueOf(body.role()));
        user.setEmail(body.email());
        user.setPassword(body.password());
        userService.save(body);
        return body;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN)")
    public User findById(@PathVariable long userId) {
        return userService.findById(userId);
    }

    @GetMapping("")
    public Page<User> GetUser(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sort) {
        return userService.getUser(page, size, sort);
    }

    @PutMapping("/{userID}")
    @PreAuthorize("hasAuthority('ADMIN)")
    public User findAndUpdate(@PathVariable long userId, @RequestBody UserDTO body) {
        return userService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userID}")
    @PreAuthorize("hasAuthority('ADMIN)")
    public void findAndDelete(@PathVariable long userId) throws CorrectDeleteUser {
        userService.findByAndDelete(userId);
        throw new CorrectDeleteUser(userId);
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticationUser) {
        if (currentAuthenticationUser == null) {
            throw new NotFoundException("user not found");
        }
        return currentAuthenticationUser;
    }

    @PutMapping("/me")
    public User upDateProfile(@AuthenticationPrincipal User currentAuthenticationUser, @RequestBody UserDTO updateUserDTO) {
        return this.userService.findByIdAndUpdate(currentAuthenticationUser.getId(), updateUserDTO);
    }
}
