package swd02_ws.laptimes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

@Entity
public class Laptime {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private Long id;
	
	@Pattern(regexp="^\\b\\d{1,2}\\b.\\b[0-5][0-9]\\b.{0,}",message="Virheellinen aika")
	@Column(name="lap", nullable=false)
	private String lap;
	
	@Column(name="date")
	private String date;

	@Column(name="weather")
	private String weather;
	
	@Column(name="video")
	private String video;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	// Car info tied to laptime so it doesn't update if user updates car in profile
	@Column(name="carModel")
	private String carModel;
	@Column(name="carEngine")
	private String carEngine;
	@Column(name="carTyres")
	private String carTyres;
	@Column(name="username")
	private String username;
	
	@ManyToOne
	@JoinColumn(name="track_id")
	private Track track;
	
	public Laptime(String lap, String date, String weather, String video, User user, Track track, String carModel, String carEngine) {
		super();
		this.lap = lap;
		this.date = date;
		this.weather = weather;
		this.video = video;
		this.user = user;
		this.track = track;
		this.carEngine = carEngine;
		this.carModel = carModel;
	}
	public Laptime(String lap, String date, String weather, User user, Track track) {
		super();
		this.lap = lap;
		this.date = date;
		this.weather = weather;
		this.user = user;
		this.track = track;
	}
	public Laptime() {}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Track getTrack() {
		return track;
	}
	public void setTrack(Track track) {
		this.track = track;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLap() {
		return lap;
	}
	public void setLap(String lap) {
		this.lap = lap;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarEngine() {
		return carEngine;
	}
	public void setCarEngine(String carEngine) {
		this.carEngine = carEngine;
	}
	public String getCarTyres() {
		return carTyres;
	}
	public void setCarTyres(String carTyres) {
		this.carTyres = carTyres;
	}
	@Override
	public String toString() {
		return "Laptime [id=" + id + ", lap=" + lap + ", date=" + date + ", weather=" + weather + ", video="
				+ video + "track" + track +"]";
	}
	
}
