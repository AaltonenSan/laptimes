package swd02_ws.laptimes.domain;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long>{
	List<Car> findByUser(User user);
}
