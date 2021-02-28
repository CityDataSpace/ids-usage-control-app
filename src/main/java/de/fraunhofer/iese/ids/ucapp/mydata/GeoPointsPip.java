package de.fraunhofer.iese.ids.ucapp.mydata;

import org.springframework.beans.factory.annotation.Autowired;

import de.fraunhofer.iese.ids.ucapp.request.Coordinate;
import de.fraunhofer.iese.ids.ucapp.service.PolygonService;
import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;

@PipService(componentName = "geo-point-pip")
public class GeoPointsPip {
	
	@Autowired
	PolygonService polygonService;
	
	@ActionDescription(methodName = "eventIn")
	public boolean event(@ActionParameterDescription(name = "geo-vertices")Coordinate[] vertices) {
		return polygonService.contains(vertices);
	}
	
}
