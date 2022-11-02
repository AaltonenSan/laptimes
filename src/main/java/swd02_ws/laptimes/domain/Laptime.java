package swd02_ws.laptimes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Laptime {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String laptime, date, weather, video;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="track_id")
	private Track track;
	
	public Laptime(String laptime, String date, String weather, String video, User user, Track track) {
		super();
		this.laptime = laptime;
		this.date = date;
		this.weather = weather;
		this.video = video;
		this.user = user;
		this.track = track;
	}
	public Laptime(String laptime, String date, String weather, User user, Track track) {
		super();
		this.laptime = laptime;
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
	public String getLaptime() {
		return laptime;
	}
	public void setLaptime(String laptime) {
		this.laptime = laptime;
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
	@Override
	public String toString() {
		return "Laptime [id=" + id + ", laptime=" + laptime + ", date=" + date + ", weather=" + weather + ", video="
				+ video + "]";
	}
	
}
