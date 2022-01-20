package de.fraunhofer.iese.ids.ucapp.service;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fraunhofer.iese.ids.ucapp.exception.BadRequestException;
import de.fraunhofer.iese.ids.ucapp.exception.QueryFailedException;

@Service
public class SpatialService {
	private static final Logger LOG = LoggerFactory.getLogger(SpatialService.class);

  private String spatial = null;

  public String getSpatial() {
    return spatial; // TODO implement me
  }

  private Model placeRdfModel;

	@Autowired
	SpatialService(Model model) {
		this.placeRdfModel = model;
	}

	

	public void setSpatial(String spatial) throws QueryFailedException {
		this.spatial = getOntology(spatial);
		// Can initialize a rdf model to make more queries
		//this.spatialRdfModel = FileManager.get().loadModel(spatial);
	}

	public String getOntology(String region) throws QueryFailedException {
		String ontology = null;

		String queryString = "PREFIX geo: <http://www.geonames.org/ontology#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
				+ "SELECT * WHERE { ?x skos:notation ?notation . " + "FILTER( ?notation =\"" + region + "\")" + "}";

		ontology = execSelectQuery(queryString, placeRdfModel).getResource("x").getURI();
		return ontology;
	}

	public boolean matchSpatialPosition(String absoluteSpatialPosition) throws QueryFailedException {
		if(null == this.spatial || this.spatial.isEmpty()) {
			LOG.error("Spatial Position is not set");
			return false;
		} 
		
		String absoluteSpatialPositionOntology = getOntology(absoluteSpatialPosition);
		if(null == absoluteSpatialPositionOntology) {
			LOG.error("Could not retrieve Ontology for Absolute Spatial Position");
			throw new BadRequestException("Error with absoluteSpatialPosition");
		} else if (absoluteSpatialPositionOntology.equals(this.spatial)) {
			return true;
		} else {
			Model absoluteSpatialPositionModel = FileManager.get().loadModel(absoluteSpatialPositionOntology);
			return checkIfTheAbsolutePositionIsInSpatial(absoluteSpatialPositionModel);
		}
	}
	
	// The isPartOfQuery will check DE-RLP model to see it is a part
	// of the DE. THe hasPartQuery will check DE model if it hasPart DE-RLP
	private boolean checkIfTheAbsolutePositionIsInSpatial(Model absoluteSpatialPositionModel) throws QueryFailedException {

		String isPartOfQuery = "PREFIX geo: <http://www.geonames.org/ontology#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " + "PREFIX dc: <http://purl.org/dc/terms/> "
				+ "ASK WHERE { ?region dc:isPartOf ?country . "
				+ "FILTER( regex(str(?country), \"" + this.spatial + "\"))} GROUP BY ?x";

//		String hasPartQuery = "PREFIX geo: <http://www.geonames.org/ontology#> "
//				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
//				+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " + "PREFIX dc: <http://purl.org/dc/terms/> "
//				+ "ASK WHERE { ?country dc:hasPart ?region . "
//				+ "FILTER( regex(str(?region), \"" + this.spatial + "\"))}";
		
		return execAskQuery(isPartOfQuery, absoluteSpatialPositionModel);
		
	}

	private boolean execAskQuery(String queryString, Model model) throws QueryFailedException {
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			return qexec.execAsk();
		}catch (Exception e) {
			throw new QueryFailedException(e.getMessage());
		} finally {
			qexec.close();
		}
	}

	private QuerySolution execSelectQuery(String queryString, Model model) throws QueryFailedException {
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try {
			ResultSet results = qexec.execSelect();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				return soln;
			}
		} catch (Exception e) {
			throw new QueryFailedException(e.getMessage());
		} finally {
			qexec.close();
		}
		return null;
	}

}
