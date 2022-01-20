package de.fraunhofer.iese.ids.ucapp.mydata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.fraunhofer.iese.ids.ucapp.exception.QueryFailedException;
import de.fraunhofer.iese.ids.ucapp.service.SpatialService;
import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;

@PipService(componentName = "spatial-pip")
public class SpatialPip {
  private static final Logger LOG = LoggerFactory.getLogger(SpatialPip.class);

  private final SpatialService spatialService;

  @Autowired
  public SpatialPip(SpatialService spatialService) {
    this.spatialService = spatialService;
  }
  
  // Pass the region code of Deutschland as DE to see whether i
  @ActionDescription(methodName = "absoluteSpatialPosition")
  public boolean spatial(@ActionParameterDescription(name = "absoluteSpatialPosition-uri")String absoluteSpatialPosition) throws QueryFailedException {
	  return spatialService.matchSpatialPosition(absoluteSpatialPosition);
  }
}
