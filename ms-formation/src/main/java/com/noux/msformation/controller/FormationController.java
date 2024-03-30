package com.noux.msformation.controller;

import com.noux.msformation.entities.Formation;
import com.noux.msformation.models.Etudiant;
import com.noux.msformation.proxies.EtudiantProxy;
import com.noux.msformation.repositories.FormationRepository;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FormationController {

    @Resource
    FormationRepository formationRepository;

    @Resource
    EtudiantProxy etudiantProxy;

    // http://localhost:8081/api/v1/formations/{id}
    @GetMapping("/formations/{id}")
    public ResponseEntity<?> getFormationWithEtudiants(@PathVariable("id") Long id) {
        Optional<Formation> formationOpt = formationRepository.findById(id);
        if (formationOpt.isPresent()) {
            Formation existFormation = formationOpt.get();
            List<Etudiant> allEtudiants = new ArrayList<>(etudiantProxy.getEtudiants(id, "to-formation").getContent());
            existFormation.setEtudiants(allEtudiants);
            return ResponseEntity.ok(existFormation);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Formation not found with id " + id);
    }

    // http://localhost:8081/api/v1/formations
    @GetMapping("/formations")
    public ResponseEntity<List<Formation>> getFormationsWithEtudiants() {
        List<Formation> allFormations = formationRepository.findAll();
        allFormations.forEach((f) -> {
            List<Etudiant> allEtudiants = new ArrayList<>(etudiantProxy.getEtudiants(f.getIdFormation(), "to-formation").getContent());
            f.setEtudiants(allEtudiants);
        });
        return ResponseEntity.ok(allFormations);
    }
}
