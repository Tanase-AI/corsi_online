package org.generation.italy.corsi_online.controller;

import java.util.Collections;
import java.util.ArrayList;
import org.generation.italy.corsi_online.model.Corso;
import org.generation.italy.corsi_online.repository.CorsoRepository;
import org.generation.italy.corsi_online.repository.DateEsamiRepository;
import org.generation.italy.corsi_online.repository.EsamiSuperatiRepository;
import org.generation.italy.corsi_online.repository.PPARepository;
import org.generation.italy.corsi_online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class MainController {

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

    @GetMapping("/elenco")
    public String elencoCorsi(
            Model model,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String settore,
            @RequestParam(required = false) String ordinamento) throws Exception {

        ArrayList<Corso> elencoCorsi = null;
        if (nome == null && settore == null)
            elencoCorsi = (ArrayList<Corso>) corsoRepository.findAll();
        else if (nome != null && settore == null)
            elencoCorsi = (ArrayList<Corso>) corsoRepository.findByNomeIgnoreCase(nome);
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
        model.addAttribute("elenco", elencoCorsi);

        return "corsi/elenco";
    }

    @GetMapping
    @ResponseBody
    public String index() {
        return "ciao";
    }

}
