package de.fraunhofer.iese.ids.ucapp.mydata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.fraunhofer.iese.ids.ucapp.service.StateService;
import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;

@PipService(componentName = "state-pip")
public class StatePip {

	private final StateService stateService;

	@Autowired
	public StatePip(StateService stateService) {
		this.stateService = stateService;
	}

	@ActionDescription(methodName = "state-same-as")
	public boolean stateSameAs(@ActionParameterDescription(name = "stateToMatch") String state) {
		return stateService.matchEnvState(state);
	}

	@ActionDescription(methodName = "state-in")
	public boolean stateIn(@ActionParameterDescription(name = "statesToMatch") List<String> states) {
		return stateService.matchEnvState(states);
	}

}
