package swd02_ws.laptimes.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<Track, Long>{
	Track findByName(String name);
	
	List<Track> findAllByOrderByName();
	
}
