package swd02_ws.laptimes.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LaptimeRepository extends CrudRepository<Laptime, Long>{
	List<Laptime> findByDate(String date);
}
