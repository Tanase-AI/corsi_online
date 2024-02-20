package org.generation.italy.corsi_online.controller;

import java.util.Optional;

import org.generation.italy.corsi_online.model.Corso;
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
@RequestMapping("/Marketing")
public class MarketingController {
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

    // -----------------------------------------------------------------------------------------------
    @GetMapping("/nuovo")
    public String nuovoCorsoGet(Model model) {

        Corso c = new Corso();
        model.addAttribute("corso", c);

        return "/corsi/nuovo";
    }

    @PostMapping("/nuovo")
    public String nuovoCorsoPost(@ModelAttribute("corso") Corso c) {

        corsoRepository.save(c);
        return "redirect:/Corso/elenco";
    }

    // -----------------------------------------------------------------------------------------------
    @GetMapping("/modifica/{id}") // restituisce la pagina di modifica (richiesta GET)
    public String modificaCorsoGet(Model model, @PathVariable("id") short id) {

        Optional<Corso> optCorso = corsoRepository.findById(id);
        if (optCorso.isPresent()) {
            Corso c = optCorso.get();

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
            return "/corsi/modifica";
        }

        corsoRepository.save(c);

        return "redirect:/Prodotti/elenco";
    }

    // -----------------------------------------------------------------------------------------------
    // @Transactional
    // @GetMapping("/elimina/{id}")
    // public String eliminaCorso(
    // @PathVariable short id) {
    // Optional<Corso> optCorso = corsoRepository.findById(id);
    // if (optCorso.isPresent()) {
    // Corso corso = optCorso.get();
    // esamiSuperatiRepository.deleteByCorso(corso);
    // dateEsamiRepository.deleteByCorso(corso);
    // corsoRepository.deleteById(id);

    // return "redirect:/corsi/error";
    // } else
    // return "nontrovato";
    // }

}
