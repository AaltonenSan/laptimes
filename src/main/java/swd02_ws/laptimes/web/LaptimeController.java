package swd02_ws.laptimes.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import swd02_ws.laptimes.domain.LaptimeRepository;
import swd02_ws.laptimes.domain.TrackRepository;
import swd02_ws.laptimes.domain.User;
import swd02_ws.laptimes.domain.UserRepository;

@Controller
public class LaptimeController {

	@Autowired
	private LaptimeRepository lrepo;
	@Autowired
	private TrackRepository trepo;
	@Autowired
	private UserRepository urepo;
	
	@GetMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("laptimes", lrepo.findAll());
		return "index";
	}
	@GetMapping("/tracks")
	public String showTracks(Model model) {
		model.addAttribute("tracks", trepo.findAll());
		return "tracklist";
	}
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
	@GetMapping("/profile/")
	public String showProfile(Model model, Principal principal) {
		model.addAttribute("cars", urepo.findByUsername(principal.getName()).getCars());
		return "profile";
	}
	@GetMapping("/tracks/{name}")
	public String showLaptimes(@PathVariable("name") String trackName, Model model) {
		model.addAttribute("track", trepo.findByName(trackName));
		return "laptimes";
	}
	
	// New user registeration
	@PostMapping("/register/")
	public String addUser(User user, RedirectAttributes redirectAttributes) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPasswordHash());
		user.setPasswordHash(encodedPassword);
		
		urepo.save(user);
		redirectAttributes.addFlashAttribute("msg", "Rekisteröityminen onnistui");
		return "redirect:/login/";
	}
}
