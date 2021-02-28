package de.fraunhofer.iese.ids.ucapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.iese.ids.ucapp.model.entity.Event;

public interface EventRepository extends JpaRepository<Event, String> {
	
	Optional<Event> findByEventUrl(String eventUrl);
}
