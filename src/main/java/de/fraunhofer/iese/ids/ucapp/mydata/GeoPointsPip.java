package de.fraunhofer.iese.ids.ucapp.mydata;

import org.springframework.beans.factory.annotation.Autowired;

import de.fraunhofer.iese.ids.ucapp.exception.BadRequestException;
import de.fraunhofer.iese.ids.ucapp.request.Coordinate;
import de.fraunhofer.iese.ids.ucapp.service.PolygonService;
import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;

/**
 * 
 * @author Jayanth Siddamsetty
 * Pass at least 3 co-ordinates of a polygon to test whether the position is inside this polygon
 *
 */

@PipService(componentName = "geo-point-pip")
public class GeoPointsPip {
	
	@Autowired
	PolygonService polygonService;
	
	@ActionDescription(methodName = "eventIn")
	public boolean event(@ActionParameterDescription(name = "geo-vertices")Coordinate[] vertices) {
		if(null != vertices && vertices.length<3 && vertices.length>8) {
			throw new BadRequestException("Number of vertices must be between 3 and 8");
		}
		return polygonService.contains(vertices);
	}
	
}
