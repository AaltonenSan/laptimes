package swd02_ws.laptimes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Track {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private Long id;
	
	@Column(name="name")
	private String name;
	@Column(name="location")
	private String location;
	@Column(name="imageUrl")
	private String imageUrl;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "track")
	@OrderBy("lap ASC")
	private List<Laptime> laptimes;
	
	public Track(String name, String location, String imageUrl) {
		super();
		this.name = name;
		this.location = location;
		this.imageUrl = imageUrl;
	}
	public Track() {}
	
	public List<Laptime> getLaptimes() {
		return laptimes;
	}
	public void setLaptimes(List<Laptime> laptimes) {
		this.laptimes = laptimes;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
}
