package swd02_ws.laptimes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String model, engine, tyres, desc;
	private Integer my;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Car(String model, String engine, String tyres, String desc, Integer my, User user) {
		super();
		this.model = model;
		this.engine = engine;
		this.tyres = tyres;
		this.desc = desc;
		this.my = my;
		this.user = user;
	}
	public Car(String model, String engine, String tyres, Integer my, User user) {
		super();
		this.model = model;
		this.engine = engine;
		this.tyres = tyres;
		this.my = my;
		this.user = user;
	}
	public Car() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getTyres() {
		return tyres;
	}
	public void setTyres(String tyres) {
		this.tyres = tyres;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getMy() {
		return my;
	}
	public void setMy(Integer my) {
		this.my = my;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", engine=" + engine + ", tyres=" + tyres
				+ ", desc=" + desc + ", year=" + my + "]";
	}
	
}
