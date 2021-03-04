package de.fraunhofer.iese.ids.ucapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.fraunhofer.iese.ids.ucapp.exception.QueryFailedException;
import de.fraunhofer.iese.ids.ucapp.request.Coordinate;
import de.fraunhofer.iese.ids.ucapp.service.PolygonService;
import de.fraunhofer.iese.ids.ucapp.service.PurposeDeterminationService;
import de.fraunhofer.iese.ids.ucapp.service.SpatialService;

/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 */
@Controller
@RequestMapping(path = "demo/set")
public class DemoConfigController {
  //TODO just for demonstration, remove later
  private final static Logger LOG = LoggerFactory.getLogger(DemoConfigController.class);

  private final SpatialService spatialService;
  private final PurposeDeterminationService purposeDeterminationService;
  private final PolygonService polygonService;
  

  @Autowired
  public DemoConfigController(SpatialService spatialService, PurposeDeterminationService purposeDeterminationService, PolygonService polygonService) {
    this.spatialService = spatialService;
    this.purposeDeterminationService = purposeDeterminationService;
    this.polygonService = polygonService;
  }

  @PutMapping(value = "/spatial", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void setSpatial(@RequestBody String spatial) throws QueryFailedException {
    this.spatialService.setSpatial(spatial.replaceAll("\"", ""));
  }

  @PutMapping(value = "/purpose", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void setPurpose(@RequestBody String purpose) {
    this.purposeDeterminationService.setPurpose(purpose.replaceAll("\"", ""));
  }
  
  @PutMapping(value = "/position", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void setPosition(@RequestBody Coordinate pos) {
    this.polygonService.setPosition(pos);
  }
 
}
