package valerio.U5W3D5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valerio.U5W3D5.entity.Event;
import valerio.U5W3D5.entity.User;
import valerio.U5W3D5.exceptions.BadRequestException;
import valerio.U5W3D5.exceptions.NotFoundException;
import valerio.U5W3D5.payloads.EventDTO;
import valerio.U5W3D5.repositories.EventDAO;
import valerio.U5W3D5.repositories.UserDAO;

@Service
public class EventService {
    @Autowired
    private EventDAO eventDao;

    @Autowired
    private UserDAO userDao;


    public Event findById(long EventId) {
        return this.eventDao.findById(EventId).orElseThrow(() -> new NotFoundException(EventId));
    }

    public Event createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.name());
        event.setDescriptionEvent(eventDTO.descriptionEvent());
        event.setDateEvent(eventDTO.dateEvent());
        event.setMaxParticipants((eventDTO.maxParticipants()));
        return eventDao.save(event);
    }

    public Event findByIdAndUpdate(long id, EventDTO updateEvent) {
        Event newEvent = this.findById(id);
        newEvent.setName(updateEvent.name());
        newEvent.setDescriptionEvent(updateEvent.descriptionEvent());
        newEvent.setDateEvent(updateEvent.dateEvent());
        newEvent.setMaxParticipants(updateEvent.maxParticipants());
        return this.eventDao.save(newEvent);
    }


    public Page<Event> getEvent(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventDao.findAll(pageable);
    }

    public Event BookEvent(Long eventId, Long userId) {
        Event event = eventDao.findById(eventId).orElseThrow(() -> new NotFoundException("evento non trovato"));

        if (event.getParticipantsCount() >= event.getMaxParticipants()) {
            throw new BadRequestException("ci dispiace informarla che i partecipanti a questo evento hanno raggiunto il numero massimo ");
        }
        User user = userDao.findById(userId).orElseThrow(() -> new NotFoundException("utente non trovato"));
        event.getParticipants().add(user);
        event.setParticipantsCount(event.getParticipantsCount() + 1);
        return eventDao.save(event);
    }

}
