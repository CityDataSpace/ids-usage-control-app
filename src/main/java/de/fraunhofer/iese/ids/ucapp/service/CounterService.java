package de.fraunhofer.iese.ids.ucapp.service;

import org.springframework.stereotype.Service;

import de.fraunhofer.iese.ids.ucapp.exception.BadRequestException;

@Service
public class CounterService {
	
	private int count = 0;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int incrementCount() {
		this.count++;
		return this.count;
	}
	
	public Boolean doOperation(int quantity, String operation) {
		
		switch(operation) {
		case "GT":
			return this.count > quantity;
		case "GTEQ":
			return this.count >= quantity;
		case "EQ":
			return this.count == quantity;
		case "LTEQ":
			return this.count <= quantity;
		case "LT":
			return this.count < quantity;
		default:
			throw new BadRequestException("Allowed operations are GT, GTEQ, EQ, LTEQ, LT");
		}
	}

}
