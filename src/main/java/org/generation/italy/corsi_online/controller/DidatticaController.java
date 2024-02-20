package org.generation.italy.corsi_online.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.corsi_online.model.Corso;
import org.generation.italy.corsi_online.model.DateEsami;
import org.generation.italy.corsi_online.model.User;
import org.generation.italy.corsi_online.repository.CorsoRepository;
import org.generation.italy.corsi_online.repository.DateEsamiRepository;
import org.generation.italy.corsi_online.repository.EsamiSuperatiRepository;
import org.generation.italy.corsi_online.repository.PPARepository;
import org.generation.italy.corsi_online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Didattica")
public class DidatticaController {

	@Autowired
	CorsoRepository corsoRepository;

	@Autowired
	DateEsamiRepository dateEsamiRepository;

	@Autowired
	PPARepository ppaRepository;

	@Autowired
	EsamiSuperatiRepository esamiSuperatiRepository;

	@Autowired
	UserRepository userRepository;

	// -------------------------------------------------------------------------------------------------------------

	@GetMapping("/modifica/{id}") // restituisce la pagina di modifica (richiesta GET)
	public String modificaCorsoGet(Model model, @PathVariable("id") short id) {

		Optional<Corso> optCorso = corsoRepository.findById(id);
		if (optCorso.isPresent()) {
			Corso c = optCorso.get();

			List<DateEsami> dateEsami = dateEsamiRepository.findAll();

			model.addAttribute("dateEsami", dateEsami);
			model.addAttribute("corso", c);
			return "/prodotti/modifica";
		} else
			return "nontrovato";
	}

	@PostMapping("/modifica")
	public String modificaCorsoPost(Model model,
			@Valid @ModelAttribute("corso") Corso c,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<DateEsami> dateEsami = dateEsamiRepository.findAll();

			model.addAttribute("dateEsami", dateEsami);
			return "/corsi/modifica";
		}

		corsoRepository.save(c);

		return "redirect:/Prodotti/elenco";
	}

	// ------------------------------------------------------------------------------------------------------------
	// ESAMI SUPERATI get
	@GetMapping("/{userId}/esamiSuperati")
	public String viewEsamiSuperati(@PathVariable int userId, Model model) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			model.addAttribute("user", user);
			model.addAttribute("esamiSuperati", esamiSuperatiRepository.findByStudente(user));
			return "user/esamiSuperati";
		} else {
			return "redirect:/error"; // Gestire il caso in cui l'utente non esiste
		}
	}

}
