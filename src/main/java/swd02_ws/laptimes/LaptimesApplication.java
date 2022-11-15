package swd02_ws.laptimes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd02_ws.laptimes.domain.Car;
import swd02_ws.laptimes.domain.CarRepository;
import swd02_ws.laptimes.domain.Laptime;
import swd02_ws.laptimes.domain.LaptimeRepository;
import swd02_ws.laptimes.domain.Track;
import swd02_ws.laptimes.domain.TrackRepository;
import swd02_ws.laptimes.domain.User;
import swd02_ws.laptimes.domain.UserRepository;


@SpringBootApplication
public class LaptimesApplication {
	
	private static final Logger log = LoggerFactory.getLogger(LaptimesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LaptimesApplication.class, args);
	}
	
	// Database test data
	@Bean
	public CommandLineRunner demo(CarRepository crepo, LaptimeRepository lrepo, TrackRepository trepo, UserRepository urepo) {
		return (args) -> {
			log.info("Save some sample users");
			urepo.save(new User("admin", "$2a$10$vaUW5L0IERz4iBgxwYHLe.qgisoV4KvWnaO4DgVQ8OQjsYiMLtquW", "admin@gmail.com", "ADMIN"));
			urepo.save(new User("user", "$2a$10$lFMki69/nrX17q3LHpBefufs8urYCR/lEQ2s2EYPmlmlp5MBajZuG", "user@gmail.com", "USER"));
			
			log.info("Save some sample tracks");
			trepo.save(new Track("Ahvenisto", "Hämeenlinna", "../images/tracks/ahvenisto.jpg"));
			trepo.save(new Track("Alastaro", "Alastaro", "../images/tracks/alastaro.jpg"));
			trepo.save(new Track("KymiRing", "Iitti", "../images/tracks/kymiring.png"));
			trepo.save(new Track("Botniaring", "Jurva", "../images/tracks/botnia.jpg"));
			trepo.save(new Track("Kemora", "Veteli", "../images/tracks/kemora.jpg"));
			trepo.save(new Track("Motopark", "Virtasalmi", "../images/tracks/motopark.jpg"));
			trepo.save(new Track("Pesämäki", "Honkajoki", "../images/tracks/pesamaki.jpg"));
			
			log.info("Save some sample cars to user");
			crepo.save(new Car("NA", "1.6", "Federal 595", 1992, urepo.findByUsername("user")));
			crepo.save(new Car("NB", "1.8", "Toyo R888", 1999, urepo.findByUsername("admin")));
			
			log.info("Save some sample laptimes");
			lrepo.save(new Laptime("1.38.02", "12.07.2018", "Kuiva", "videoUrl" ,urepo.findByUsername("user"), trepo.findByName("Ahvenisto"), "1.8", "NA"));
			lrepo.save(new Laptime("1.33.06", "22.06.2016", "Kuiva +24", "youtube.com/jokuvideo", urepo.findByUsername("user"), trepo.findByName("Ahvenisto"), "1.8", "NA"));
			lrepo.save(new Laptime("1.33.54", "15.08.2022", "Kuiva", "videoUrl", urepo.findByUsername("user"), trepo.findByName("Alastaro"), "1.8", "NA"));
			
		};
	};

}
