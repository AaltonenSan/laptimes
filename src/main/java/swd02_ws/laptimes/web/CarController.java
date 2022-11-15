package swd02_ws.laptimes.web;

import java.security.Principal;
import java.time.Year;
import java.util.Optional;

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

import swd02_ws.laptimes.domain.Car;
import swd02_ws.laptimes.domain.CarRepository;
import swd02_ws.laptimes.domain.UserRepository;

@Controller
public class CarController {

	@Autowired
	private CarRepository crepo;
	@Autowired
	private UserRepository urepo;
	
	// Form to add a new car
	@GetMapping("/car")
	public String addCar(Model model, Principal principal) {
		if (!urepo.findByUsername(principal.getName()).getCars().isEmpty()) {
			return "redirect:/profile";
		}
		model.addAttribute("car", new Car());
		model.addAttribute("year", Year.now().getValue() + 1); // Keep the model year list updated
		return "carform";
	}
	// User can only edit own cars
	@PreAuthorize("#username == authentication.principal.username" + 
			  "|| hasAuthority('ADMIN')")
	@GetMapping("/editcar/{username}/{id}")
	public String editCar(@PathVariable("username") String username,  @PathVariable("id") Long id, Model model) {
		Optional<Car> car = crepo.findById(id);
		if (car.isEmpty()) {
			return "redirect:/profile";
		}
		model.addAttribute("mk", car.get().getModel());
		model.addAttribute("myear", car.get().getMy());
		model.addAttribute("car", car);
		model.addAttribute("year", Year.now().getValue() + 1); // Keep the model year list updated
		return "carform";
	}
	
	// User can only delete own cars
	@PreAuthorize("#username == authentication.principal.username" + 
				  "|| hasAuthority('ADMIN')")
	@GetMapping("/deletecar/{username}/{id}")
	public String deleteCar(@PathVariable("username") String username, @PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		crepo.deleteById(id);
		redirectAttributes.addFlashAttribute("msg", "Auto poistettu");
		return "redirect:/profile";
	}
	// Save car to db
	@PostMapping("/car")
	public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, Principal principal, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("year", Year.now().getValue() + 1); // Keep the model year list updated
			return "carform";
		}
		car.setUser(urepo.findByUsername(principal.getName()));
		crepo.save(car);
		return "redirect:/profile";
		
	}
}
