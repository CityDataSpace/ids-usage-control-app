package de.fraunhofer.iese.ids.ucapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.iese.ids.ucapp.model.entity.Event;
import de.fraunhofer.iese.ids.ucapp.request.EventRequest;
import de.fraunhofer.iese.ids.ucapp.service.EventService;

@RestController
public class EventController {

	final Logger logger = LoggerFactory.getLogger(EventController.class);

	@Autowired
	EventService eventService;

	@RequestMapping(value = "/events/add", method = RequestMethod.POST, consumes="application/json")
	ResponseEntity<String> addEvent(@RequestBody EventRequest event) {
		eventService.addEvent(event.getEvent(), event.getStart(), event.getEnd());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/events/get", method = RequestMethod.GET)
	ResponseEntity<List<Event>> getEvents() {
		return ResponseEntity.ok(eventService.getEvents());
	}

	@RequestMapping(value = "/events/delete", method = RequestMethod.DELETE)
	ResponseEntity<Object> deleteEventByUrl(@RequestBody String eventUrl) {
		eventService.deleteEvent(eventUrl);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
