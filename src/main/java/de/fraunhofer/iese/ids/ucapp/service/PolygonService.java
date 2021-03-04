package de.fraunhofer.iese.ids.ucapp.service;

import org.springframework.stereotype.Service;

import de.fraunhofer.iese.ids.ucapp.request.Coordinate;

@Service
public class PolygonService {
	
	private Coordinate position = null;
	
	public void setPosition(Coordinate pos) {
		this.position = pos;
	}
	
	// Ray Casting method
	public boolean contains(Coordinate[] vertices) {
		int i;
		int j;
		boolean result = false;
		for (i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
			if ((vertices[i].lon > this.position.lon) != (vertices[j].lon > this.position.lon)
					&& (this.position.lat < (vertices[j].lat - vertices[i].lat) * (this.position.lon - vertices[i].lon)
							/ (vertices[j].lon - vertices[i].lon) + vertices[i].lat)) {
				result = !result;
			}
		}
		return result;
	}
}