package org.generation.italy.corsi_online.controller;

import java.util.Optional;

import org.generation.italy.corsi_online.model.Corso;
import org.generation.italy.corsi_online.model.PPA;
import org.generation.italy.corsi_online.model.User;
import org.generation.italy.corsi_online.repository.CorsoRepository;
import org.generation.italy.corsi_online.repository.DateEsamiRepository;
import org.generation.italy.corsi_online.repository.EsamiSuperatiRepository;
import org.generation.italy.corsi_online.repository.PPARepository;
import org.generation.italy.corsi_online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/Studente")
public class StudenteController {

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

    public String index() {
        return "test";
    }

    // ------------------------------------------------------------------------------------------------------------
    // ELENCO
    @GetMapping("/{userId}/corsi")
    public String viewCorsi(@PathVariable int userId, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("corsi", corsoRepository.findAll());
            return "corsi/elenco";
        } else {
            return "redirect:/error"; // Gestire il caso in cui l'utente non esiste
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // PRENOTA post
    @PostMapping("/{userId}/prenotazione")
    public String prenota(@PathVariable int userId, @ModelAttribute PPA prenotazione) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            prenotazione.setStudente(user);
            ppaRepository.save(prenotazione);
            return "redirect:/Studente/user/" + userId + "/prenotazioni";
        } else {
            return "redirect:/error"; // Gestire il caso in cui l'utente non esiste
        }
    }
    // PRENOTA post

    @GetMapping("/{userId}/prenotazioni")
    public String viewPrenotazioni(@PathVariable int userId, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("prenotazioni", ppaRepository.findByStudente(user));
            return "PPA/elenco";
        } else {
            return "redirect:/error"; // Gestire il caso in cui l'utente non esiste
        }
    }

    @GetMapping("/{userId}/corso/dettaglio/{corsoId}")
    public String dettaglioCorso(@PathVariable short corsoId, int userId, Model model) {
        Optional<Corso> corsoOptional = corsoRepository.findById(corsoId);
        if (corsoOptional.isPresent()) {
            Corso corso = corsoOptional.get();
            model.addAttribute("corso", corso);
            return "corsi/dettaglio";
        } else {
            return "redirect:/error";
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    @GetMapping("/elimina/{id}")
    public String eliminaPPA(
            @PathVariable long id) {
        Optional<Corso> optPPA = ppaRepository.findById(id);
        if (optPPA.isPresent()) // il prodotto è stato trovato
        {
            ppaRepository.deleteById(id);
            return "redirect:/Corsi/elenco";
        } else
            return "nontrovato";
    }

}
