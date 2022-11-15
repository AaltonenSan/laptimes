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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="usertable")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private Long id;
	
	@NotBlank(message = "Käyttänimi on pakollinen")
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@NotBlank(message = "Sähköposti on pakollinen")
	@Column(name="email", nullable=false)
	private String email;
	
	@Size(min=5, message = "Salasanan tulee olla vähintään 5 merkkiä")
	@Column(name="password", nullable=false)
	private String passwordHash;
	
	@Column(name="joinDate")
	private java.sql.Date joinDate;
	
	@Column(name="role", nullable=false)
	private String role;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Car> cars;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	@OrderBy("track ASC, lap ASC")
	private List<Laptime> laptimes;
	
	public User(String username, String passwordHash, String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}
	public User() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Laptime> getLaptimes() {
		return laptimes;
	}
	public void setLaptimes(List<Laptime> laptimes) {
		this.laptimes = laptimes;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.sql.Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(java.sql.Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", email=" + email
				+ ", role=" + role + "]";
	}
	
}
