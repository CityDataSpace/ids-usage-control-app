package de.fraunhofer.iese.ids.ucapp.service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.iese.ids.ucapp.exception.BadRequestException;
import de.fraunhofer.iese.ids.ucapp.exception.ResourceNotFoundException;
import de.fraunhofer.iese.ids.ucapp.model.entity.Event;
import de.fraunhofer.iese.ids.ucapp.repository.EventRepository;

/**
 * 
 * @author Jayanth Siddamsetty
 * Other option: Convert the urls to URL objects. The suggested code is in comments. It allows to match
 * events using url.getHost()
 *
 *
 */
@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public void addEvent(String eventUrl, String startDate, String endDate) {
		// URL url = null;
		Instant start = null;
		Instant end = null;
		try {
			// url = new URL(eventUrl);
			// Match with url.getHost()?
			start = Instant.parse(startDate);
			end = Instant.parse(endDate);
		} catch(DateTimeParseException ex) {
			throw new BadRequestException(ex.getMessage());
		}

		eventRepository.save(new Event(eventUrl, start, end));
	}

	public List<Event> getEvents() {
		List<Event> events = new ArrayList<Event>();
		eventRepository.findAll().forEach(events::add);
		return events;
	}

	public Event getEventByUrl(String eventUrlStr) {

//		URL eventUrl = null;
//		try {
//			eventUrl = new URL(eventUrlStr);
//		} catch (MalformedURLException e) {
//			throw new BadRequestException(e.getMessage());
//		}

		Optional<Event> optionalEvent = eventRepository.findByEventUrl(eventUrlStr);

		if (optionalEvent.isPresent()) {
			return optionalEvent.get();
		} else {
			throw new ResourceNotFoundException("Event not found!");
		}
	}

	public void deleteEvent(String eventUrlStr) {
//		URL eventUrl = null;
//		try {
//			eventUrl = new URL(eventUrlStr);
//		} catch (MalformedURLException e) {
//			throw new BadRequestException(e.getMessage());
//		}
		eventRepository.deleteById(eventUrlStr);
	}

	public boolean checkEventIsOpen(String eventUrlStr){
//		URL eventUrl = null;
//		try {
//			eventUrl = new URL(eventUrlStr);
//		} catch (MalformedURLException e) {
//			throw new BadRequestException(e.getMessage());
//		}

		Optional<Event> optionalEvent = eventRepository.findById(eventUrlStr);

		if (optionalEvent.isPresent()) {
			Event event = optionalEvent.get();
			Instant today = Instant.now();
			
			if((event.getStartDate().isBefore(today) || event.getStartDate().equals(today)) &&
					(event.getEndDate().isAfter(today))) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new ResourceNotFoundException("Event not found!");
		}
	}

}
