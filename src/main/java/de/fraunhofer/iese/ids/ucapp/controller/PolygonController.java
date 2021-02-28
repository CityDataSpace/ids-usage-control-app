package de.fraunhofer.iese.ids.ucapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.iese.ids.ucapp.exception.BadRequestException;
import de.fraunhofer.iese.ids.ucapp.request.Coordinate;
import de.fraunhofer.iese.ids.ucapp.request.PolygonRequest;
import de.fraunhofer.iese.ids.ucapp.service.PolygonService;

@RestController
public class PolygonController {

	final Logger logger = LoggerFactory.getLogger(PolygonController.class);

	@Autowired
	PolygonService polygonService;
	
	@RequestMapping(value="/polygon/setposition", method = RequestMethod.PUT, consumes="application/json")
	ResponseEntity<Object> setPosition(@RequestBody Coordinate pos){
		polygonService.setPosition(pos);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/polygon/checkposition", method = RequestMethod.GET, consumes="application/json")
	ResponseEntity<Boolean> checkLocation(@RequestBody PolygonRequest request) {
		Coordinate[] vertices = request.getVertices();
		if(null != vertices && vertices.length<3 && vertices.length>8) {
			throw new BadRequestException("Number of vertices must be between 3 and 8");
		}
		return ResponseEntity.ok(polygonService.contains(vertices));
	}
}
