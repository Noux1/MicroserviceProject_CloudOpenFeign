package com.noux.msformation.controller;

import com.noux.msformation.entities.Formation;
import com.noux.msformation.models.Etudiant;
import com.noux.msformation.proxies.EtudiantProxy;
import com.noux.msformation.repositories.FormationRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FormationController {

    @Resource
    FormationRepository formationRepository;

    @Resource
    EtudiantProxy etudiantProxy;

    @GetMapping("/formations/{id}")
    Formation getFormationWIthEtudiants(@PathVariable("id") Long id)
    {
        Formation existFormation = formationRepository.findById(id).get();
        List<Etudiant> AllEtudiants = new ArrayList(etudiantProxy.getEtudiants(id,"to-formation").getContent());
        existFormation.setEtudiants(AllEtudiants);

        return existFormation;
    }
    @GetMapping("/formations")
    public List<Formation> getFormationsWithEtudiants() {
        List<Formation> allFormations = formationRepository.findAll();
        allFormations.forEach((f) -> {
            List<Etudiant> allEtudiants = new ArrayList<>(etudiantProxy.getEtudiants(f.getIdFormation(), "to-formation").getContent());
            f.setEtudiants(allEtudiants);
        });
        return allFormations;
    }

}
