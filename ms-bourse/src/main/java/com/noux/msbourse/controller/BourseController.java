package com.noux.msbourse.controller;

import com.noux.msbourse.entities.Etudiant;
import com.noux.msbourse.proxies.ScolariteProxy;
import com.noux.msbourse.repositories.EtudiantRepository;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BourseController {

    @Resource
    EtudiantRepository etudiantRepository;

    @Resource
    ScolariteProxy scolariteProxy;

    // http://localhost:8083/api/v1/etudiant/{id}
    @GetMapping("/etudiant/{id}")
    public ResponseEntity<?> getEtudiantWithScolarite(@PathVariable("id") Long id) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(id);
        if (etudiantOpt.isPresent()) {
            Etudiant existEtudiant = etudiantOpt.get();
            existEtudiant.setEtudiantScolarite(scolariteProxy.getEtudiantScolarite(id, "tobourse"));
            return ResponseEntity.ok(existEtudiant);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Etudiant not found with id " + id);
    }

    // http://localhost:8083/api/v1/etudiant/all
    @GetMapping("/etudiant/all")
    public ResponseEntity<List<Etudiant>> getEtudiantsWithScolarites() {
        List<Etudiant> allEtudiants = etudiantRepository.findAll();
        allEtudiants.forEach((e) -> {
            e.setEtudiantScolarite(scolariteProxy.getEtudiantScolarite(e.getIdEtudiant(), "tobourse"));
        });
        return ResponseEntity.ok(allEtudiants);
    }
}
