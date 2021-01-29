package de.fraunhofer.iese.ids.ucapp.service;

import org.springframework.stereotype.Service;

@Service
public class SpatialService {

  // TODO just for demonstration
  private String spatial = "DE";

  public String getSpatial() {
    return spatial; // TODO implement me
  }

  // TODO just for demonstration
  public void setSpatial(String spatial) {
    this.spatial = spatial;
  }
}
