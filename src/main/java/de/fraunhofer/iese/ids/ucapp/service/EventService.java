package de.fraunhofer.iese.ids.ucapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.iese.ids.ucapp.exception.CommonServerException;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		Date start = null;
		Date end = null;
		try {
			// url = new URL(eventUrl);
			// Match with url.getHost()?
			start = sdf.parse(startDate);
			end = sdf.parse(endDate);
		} catch (ParseException e) {
			throw new CommonServerException(e.getMessage());
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
			Date today = new Date();
			if ((event.getStartDate().before(today) || event.getStartDate().equals(today))
					&& (event.getEndDate().after(today))) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new ResourceNotFoundException("Event not found!");
		}
	}

}
