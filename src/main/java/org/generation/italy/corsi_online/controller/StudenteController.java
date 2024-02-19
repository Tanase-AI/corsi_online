package org.generation.italy.corsi_online.controller;

import java.util.Optional;

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

    @GetMapping("/{userId}/corsi")
    public String viewCorsi(@PathVariable int userId, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("corsi", corsoRepository.findAll());
            return "esami/elenco";
        } else {
            return "redirect:/error"; // Gestire il caso in cui l'utente non esiste
        }
    }

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

    @GetMapping("/{userId}/ppa")
    public String viewPPA(@PathVariable int userId, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("ppaList", ppaRepository.findByStudente(user));
            return "user/ppa";
        } else {
            return "redirect:/error"; // Gestire il caso in cui l'utente non esiste
        }
    }

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

    @GetMapping("/{userId}/prenotazioni")
    public String viewPrenotazioni(@PathVariable int userId, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("prenotazioni", ppaRepository.findByStudente(user));
            return "user/prenotazioni";
        } else {
            return "redirect:/error"; // Gestire il caso in cui l'utente non esiste
        }
    }

}
