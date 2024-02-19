package org.generation.italy.corsi_online.controller;

import java.util.*;
import org.generation.italy.corsi_online.model.Corso;
import org.generation.italy.corsi_online.model.DateEsami;
import org.generation.italy.corsi_online.repository.CorsoRepository;
import org.generation.italy.corsi_online.repository.DateEsamiRepository;
import org.generation.italy.corsi_online.repository.EsamiSuperatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

public class DidatticaController {

	@Autowired
	CorsoRepository corsoRepository;

	@Autowired
	DateEsamiRepository dateEsamiRepository;

	@GetMapping("/nuovo-corso")
	public String nuovoCorsoGet(Model model) {
		Corso c = new Corso();

		model.addAttribute("corso", c);
		return "/corsi/nuovo";
	}

	@PostMapping("/nuovo-corso")
	public String nuovoCorsoPost(Model model,
			@Valid @ModelAttribute("corso") Corso c,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return "/prodotti/nuovo";
		}
		corsoRepository.save(c);

		return "redirect:/Prodotti/elenco";
	}

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

	// --------------------------------------------------------------------------------------------
	@GetMapping("/elimina/{id}")
	public String eliminaCorso(@PathVariable Integer id) {
		Optional<Corso> optCorso = corsoRepository.findById(id);
		if (optCorso.isPresent()) {
			corsoRepository.deleteById(id);
			return "redirect:/Corsi/elenco";
		} else
			return "nontrovato"; // todo html nontrovato
	}

}
