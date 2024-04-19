package valerio.U5W3D5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.U5W3D5.entity.Event;
import valerio.U5W3D5.exceptions.BadRequestException;
import valerio.U5W3D5.payloads.EventDTO;
import valerio.U5W3D5.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event createEvent(@RequestBody @Validated EventDTO eventDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result.getAllErrors());
        }
        return eventService.createEvent(eventDTO);
    }

    @PostMapping("/{eventID}/Book")
    public Event bookEvent(@PathVariable long eventID, @RequestParam long userID) {
        return eventService.BookEvent(eventID, userID);
    }

}
