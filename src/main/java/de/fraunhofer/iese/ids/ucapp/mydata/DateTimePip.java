package de.fraunhofer.iese.ids.ucapp.mydata;

import java.time.LocalDateTime;

import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;

@PipService(componentName = "date-time-pip")
public class DateTimePip {
	
	@ActionDescription(methodName = "interval")
	public boolean interval(@ActionParameterDescription(name = "interval-time-start")String startTime, 
			@ActionParameterDescription(name = "interval-time-end")String endTime) {
		LocalDateTime start = LocalDateTime.parse(startTime);
		LocalDateTime end = LocalDateTime.parse(endTime);
		LocalDateTime now = LocalDateTime.now();
		return now.isAfter(start) && now.isBefore(end);
	}
	
	@ActionDescription(methodName = "duration")
	public boolean duration(@ActionParameterDescription(name = "duration-time-start")String startTime, 
			@ActionParameterDescription(name = "duration-minutes")long durationMinutes) {
		LocalDateTime start = LocalDateTime.parse(startTime);
		return LocalDateTime.now().isBefore(start.plusMinutes(durationMinutes));
	}
	
	@ActionDescription(methodName = "instant")
	public boolean instant(@ActionParameterDescription(name = "instant-time-end")String endTime) {
		LocalDateTime end = LocalDateTime.parse(endTime);
		return LocalDateTime.now().isBefore(end);
	}
	
}
