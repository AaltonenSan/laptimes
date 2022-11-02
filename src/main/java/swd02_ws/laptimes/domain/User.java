package swd02_ws.laptimes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usertable")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private Long id;
	
	@Column(name="username", nullable=false, unique=true)
	private String username;
	@Column(name="password", nullable=false)
	private String passwordHash;
	@Column(name="email", nullable=false)
	private String email;
	@Column(name="role", nullable=false)
	private String role;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Car> cars;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
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
