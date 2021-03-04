package de.fraunhofer.iese.ids.ucapp.mydata;

import org.springframework.beans.factory.annotation.Autowired;

import de.fraunhofer.iese.ids.ucapp.service.EventService;
import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;

/**
 * 
 * @author Jayanth Siddamsetty
 * Pass the url of the event as a string to determine whether the event is happening at the moment
 *
 */

@PipService(componentName = "event-pip")
public class EventPip {
	
	@Autowired
	EventService eventService;
	
	@ActionDescription(methodName = "eventIn")
	public boolean event(@ActionParameterDescription(name = "event-uri")String eventUri) {
		return eventService.checkEventIsOpen(eventUri);
	}
}
