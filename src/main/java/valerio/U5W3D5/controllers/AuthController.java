package valerio.U5W3D5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.U5W3D5.exceptions.BadRequestException;
import valerio.U5W3D5.payloads.UserDTO;
import valerio.U5W3D5.payloads.UserLoginDTO;
import valerio.U5W3D5.payloads.UserResponseDTO;
import valerio.U5W3D5.payloads.UserResponseLoginDTO;
import valerio.U5W3D5.services.AuthService;
import valerio.U5W3D5.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserResponseLoginDTO login(@RequestBody UserLoginDTO payload) {
        return new UserResponseLoginDTO(this.authService.authenticate(payload));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(@RequestBody @Validated UserDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result.getAllErrors());
        }
        return new UserResponseDTO(this.userService.save(body).email());
    }
}
