package org.generation.italy.corsi_online.controller;

import java.util.Optional;

import java.util.Collections;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public String viewCorsi(@PathVariable int userId, Model model,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String settore,
            @RequestParam(required = false) String ordinamento) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            ArrayList<Corso> elencoCorsi = null;
            if (nome == null && settore == null)
                elencoCorsi = (ArrayList<Corso>) corsoRepository.findAll();
            else if (nome != null && settore == null)
                elencoCorsi = (ArrayList<Corso>) corsoRepository.findByNomeLike("%" + nome + "%");
            else if (nome == null && settore != null)
                elencoCorsi = (ArrayList<Corso>) corsoRepository.findBySettoreLikeOrderByPrezzo("%" + settore + "%");
            else
                elencoCorsi = (ArrayList<Corso>) corsoRepository
                        .findByNomeIgnoreCaseAndSettoreLike(nome, "%" + settore + "%");

            if (ordinamento != null) {
                if (ordinamento.equals("asc"))
                    Collections.sort(elencoCorsi);
                else if (ordinamento.equals("desc"))
                    Collections.sort(elencoCorsi, Collections.reverseOrder());
                else
                    return "Ordinamento non valido";
            }

            model.addAttribute("user", user);
            model.addAttribute("corsi", elencoCorsi);
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
    public String dettaglioCorso(@PathVariable short corsoId, @PathVariable Optional<Integer> userId, Model model) {
        if (userId.isPresent()) {
            Optional<Corso> corsoOptional = corsoRepository.findById(corsoId);
            Optional<User> userOptional = userRepository.findById(userId.get());

            if (corsoOptional.isPresent() && userOptional.isPresent()) {
                Corso corso = corsoOptional.get();
                User user = userOptional.get();
                model.addAttribute("user", user);
                model.addAttribute("corso", corso);
                return "corsi/dettaglio";
            }
        }

        return "redirect:/error";
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
