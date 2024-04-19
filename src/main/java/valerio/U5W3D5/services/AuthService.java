package valerio.U5W3D5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valerio.U5W3D5.entity.User;
import valerio.U5W3D5.exceptions.UnauthorizedException;
import valerio.U5W3D5.payloads.UserLoginDTO;
import valerio.U5W3D5.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticate(UserLoginDTO payload) {
        User user = this.userService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("credenziali non valide nome utente o password errati");
        }
    }
}
