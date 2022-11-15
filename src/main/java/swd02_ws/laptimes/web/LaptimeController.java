package swd02_ws.laptimes.web;

import java.security.Principal;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import swd02_ws.laptimes.domain.Laptime;
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
		model.addAttribute("tracks", trepo.findAllByOrderByName());
		return "tracklist";
	}
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	// Get record laps for all users on certain Track
	@GetMapping("/tracks/{name}")
	public String showLaptimes(@PathVariable("name") String trackName, Model model) {
		model.addAttribute("record", lrepo.getRecordLaps(trepo.findByName(trackName).getId()));
		model.addAttribute("track", trepo.findByName(trackName));
		return "laptimetable";
	}
	// Form to add new a laptime
	@GetMapping("/addlaptime")
	public String addLaptime(Model model, Principal principal, RedirectAttributes redirectAttributes) {
		if (urepo.findByUsername(principal.getName()).getCars().isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Lisää auto ennen kierrosajan lisäämistä!");
			return "redirect:/car";
		}
		model.addAttribute("laptime", new Laptime());
		model.addAttribute("tracks", trepo.findAllByOrderByName());
		return "laptimeform";
	}
	// Save new laptime to db
	@PostMapping("/savelap")
	public String saveLap(@Valid @ModelAttribute("laptime") Laptime laptime, BindingResult bindingResult, Principal principal, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("tracks", trepo.findAll());
			return "laptimeform";
		}
		User user = urepo.findByUsername(principal.getName());
		laptime.setUser(user);
		laptime.setCarEngine(user.getCars().get(0).getEngine());
		laptime.setCarModel(user.getCars().get(0).getModel());
		laptime.setCarTyres(user.getCars().get(0).getTyres());
		lrepo.save(laptime);
		return "redirect:/laptimes";
	}
	
	@PreAuthorize("#username == authentication.principal.username" +
			"|| hasAuthority('ADMIN')")
	// Delete laptime
	@GetMapping("/deletelaptime/{username}/{id}")
	public String deleteLaptime(@PathVariable("username") String username, @PathVariable("id") Long id) {
		lrepo.deleteById(id);
		return "redirect:/laptimes";
	}
}
