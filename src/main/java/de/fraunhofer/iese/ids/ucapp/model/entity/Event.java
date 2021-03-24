/**
 * 
 */
package de.fraunhofer.iese.ids.ucapp.model.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

	@Id
	private String eventUrl;
	
	private Instant startDate;
	private Instant endDate;
	
}
