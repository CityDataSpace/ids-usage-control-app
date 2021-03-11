package de.fraunhofer.iese.ids.ucapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.fraunhofer.iese.ids.ucapp.exception.BadRequestException;
import de.fraunhofer.iese.ids.ucapp.exception.CommonServerException;

@Service
public class StateService {
	
	private String envState = null;
	private String prevEnvState = null;
	
	public void setState(String state) {
		this.prevEnvState = this.envState;
		this.envState = state;
	} 
	
	public Boolean matchEnvState(String state) {
		if(null == envState || envState.isEmpty()) {
			throw new CommonServerException("State not set");
		}
		
		if(null!=state && !state.isEmpty()) {
			return state.equals(envState);
		} else {
			throw new BadRequestException("Invalid State passed as parameter: "+state);
		}
	}

	public Boolean matchEnvState(List<String> states) {
		if(null == envState || envState.isEmpty()) {
			throw new CommonServerException("State not set");
		}
		return states.contains(envState);
	}
	
	public String getPrevEnvState() {
		return this.prevEnvState;
	}
}
