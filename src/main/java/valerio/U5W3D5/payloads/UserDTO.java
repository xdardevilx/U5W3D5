package valerio.U5W3D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(
        @NotEmpty(message = "il nome è obbligatorio")
        String name,
        @NotEmpty(message = "il cognome è obbligatorio")
        String surname,
        @NotEmpty(message = "l'email è obbligatoria")
        @Email(message = "l'email inserita non è valida")
        String email,
        @NotEmpty(message = "la password è obbligatoria")
        String password
) {
}

