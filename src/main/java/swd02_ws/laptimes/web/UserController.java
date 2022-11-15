package swd02_ws.laptimes.web;

import java.security.Principal;
import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import swd02_ws.laptimes.domain.LaptimeRepository;
import swd02_ws.laptimes.domain.TrackRepository;
import swd02_ws.laptimes.domain.User;
import swd02_ws.laptimes.domain.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository urepo;
	@Autowired
	private LaptimeRepository lrepo;
	@Autowired
	private TrackRepository trepo;
	
	// New user registeration
	@PostMapping("/register")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		User existingUser = urepo.findByUsername(user.getUsername());
		User existingEmail =urepo.findByEmail(user.getEmail());
		if (existingUser != null && existingUser.getUsername() != null) {
			bindingResult.rejectValue("username", null, "Käyttäjänimi on jo käytössä");
		}
		if (existingEmail != null && existingEmail.getEmail() != null) {
			bindingResult.rejectValue("email", null, "Sähköposti on jo käytössä");
		}
		if (bindingResult.hasErrors()) {
			return "register";
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPasswordHash());
		user.setPasswordHash(encodedPassword);
		user.setJoinDate(new Date(System.currentTimeMillis()));
		user.setRole("USER");
		urepo.save(user);
		redirectAttributes.addFlashAttribute("msg", "Rekisteröityminen onnistui");
		return "redirect:/login";
	}
	
	// Get current users profile
	@GetMapping("/profile")
	public String showProfile(Model model, Principal principal) {
		model.addAttribute("user", urepo.findByUsername(principal.getName()));
		return "profile";
	}
	
	// Get current users lap records for each track
	@GetMapping("/laptimes")
	public String userLaptimes(Model model, Principal principal) {
		model.addAttribute("tracks", trepo.findAll());
		model.addAttribute("laptimes", lrepo.getUserRecordLaps(urepo.findByUsername(principal.getName()).getId()));
		return "userrecords";
	}
	
	// Get current users laptimes for certain track 
	@GetMapping("/laptimes/{track}")
	public String userLaptimeByTrack(@PathVariable("track") String track, Model model, Principal principal) {
		model.addAttribute("tracks", trepo.findAll());
		model.addAttribute("track", track);
		model.addAttribute("laptimes", lrepo.findByUserAndTrackOrderByLap(urepo.findByUsername(principal.getName()), trepo.findByName(track)));
		return "userlaptimes";
	}
}
