package de.fraunhofer.iese.ids.ucapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolygonRequest {
	
	private Coordinate[] vertices;

}
