package swd02_ws.laptimes.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface LaptimeRepository extends CrudRepository<Laptime, Long>{
	List<Laptime> findByDate(String date);
	
	// Get all laps for one user for certain track
	List<Laptime> findByUserAndTrackOrderByLap(User user, Track track);
	
	// List of best lap by each user for selected track
	@Query(value="SELECT u.username as Username, l.id as Id, l.lap as Lap, "
					+ "l.car_model as CarModel, l.car_engine as CarEngine, "
					+ "l.date as LapDate, l.car_tyres as CarTyres, "
					+ "l.weather as Weather, l.video as Video "
				+ "FROM Laptime l "
				+ "LEFT JOIN Usertable u ON u.id = l.user_id "
				+ "INNER JOIN (SELECT user_id, MIN(lap) as Lap, track_id "
					+ "FROM Laptime GROUP BY user_id, track_id) Record "
				+ "ON l.user_id = Record.user_id AND l.lap = Record.Lap "
				+ "WHERE l.track_id = :trackId "
				+ "ORDER BY Lap;",
			nativeQuery=true)
	List<RecordLap> getRecordLaps(Long trackId);
	
	// List of top 3 times for a track
	@Query(value="SELECT u.username as Username, l.id as Id, l.lap as Lap, "
					+ "l.car_model as CarModel, l.car_engine as CarEngine, "
					+ "l.date as LapDate, l.car_tyres as CarTyres, "
					+ "l.weather as Weather, l.video as Video "
				+ "FROM Laptime l "
				+ "LEFT JOIN Usertable u ON u.id = l.user_id "
				+ "INNER JOIN (SELECT user_id, MIN(lap) as Lap, track_id "
					+ "FROM Laptime GROUP BY user_id, track_id) Record "
				+ "ON l.user_id = Record.user_id AND l.lap = Record.Lap "
				+ "WHERE l.track_id = :trackId "
				+ "ORDER BY Lap "
				+ "LIMIT 3;",
			nativeQuery=true)
	List<RecordLap> getTopList(Long trackId);
	
	// List of current users best laps for every track user has laptime
	@Query(value="SELECT  l.lap as Lap, l.id as Id, t.name as Track,"
				+ "	l.car_model as CarModel, l.car_engine as CarEngine,"
				+ "	l.date as LapDate, l.car_tyres as CarTyres,"
				+ "	l.weather as Weather, l.video as Video, u.username as Username"
			+ "	FROM Laptime l"
			+ " LEFT JOIN Track t ON t.id = l.track_id"
			+ " LEFT JOIN Usertable u ON u.id = l.user_id"
			+ "	INNER JOIN (SELECT user_id, MIN(lap) as Lap, track_id as Track"
				+ "	FROM Laptime GROUP BY user_id, Track) Record"
			+ "	ON l.user_id = Record.user_id AND l.lap = Record.Lap"
			+ "	WHERE l.user_id = :userId"
			+ "	ORDER BY Track;", nativeQuery=true)
	List<RecordLap> getUserRecordLaps(Long userId);
	
}
