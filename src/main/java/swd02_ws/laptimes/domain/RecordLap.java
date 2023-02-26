package swd02_ws.laptimes.domain;

public interface RecordLap {

	// Interface used in LaptimeRepository
	String getUsername();
	String getId();
	String getLap();
	String getTrack();
	String getCarModel();
	String getCarEngine();
	String getLapDate();
	String getCarTyres();
	String getWeather();
	String getVideo();
}
