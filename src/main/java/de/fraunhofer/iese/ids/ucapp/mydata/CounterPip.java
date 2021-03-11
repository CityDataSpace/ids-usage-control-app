package de.fraunhofer.iese.ids.ucapp.mydata;

import org.springframework.beans.factory.annotation.Autowired;

import de.fraunhofer.iese.ids.ucapp.service.CounterService;
import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;

@PipService(componentName = "counter-pip")
public class CounterPip {

	private final CounterService counterService;

	@Autowired
	public CounterPip(CounterService counterService) {
		this.counterService = counterService;
	}

	@ActionDescription(methodName = "count-get")
	public int getCount() {
		return this.counterService.getCount();
	}
	
	@ActionDescription(methodName = "countOperation")
	public boolean doCountOperation(@ActionParameterDescription(name = "count-quantity") int quantity, 
			@ActionParameterDescription(name = "count-operation") String operation) {
		return this.counterService.doOperation(quantity, operation);
	}


}
