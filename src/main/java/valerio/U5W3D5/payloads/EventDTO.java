package valerio.U5W3D5.payloads;

import jakarta.validation.constraints.NotEmpty;

public record EventDTO(
        @NotEmpty(message = "il nome dell'evento è obbligatorio")
        String name,
        @NotEmpty(message = "la descrizione dell'evento è obbligatoria")
        String descriptionEvent,
        @NotEmpty(message = "la data dell'evento è obbligatoria")
        String dateEvent,
        @NotEmpty(message = "il numero massimo di partecipanti è obbligatorio")
        String maxParticipants

) {
}
