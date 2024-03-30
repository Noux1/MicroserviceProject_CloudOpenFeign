package com.noux.msbourse.controller;

import com.noux.msbourse.entities.Etudiant;
import com.noux.msbourse.proxies.ScolariteProxy;
import com.noux.msbourse.repositories.EtudiantRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BourseController {

    @Resource
    EtudiantRepository etudiantRepository;

    @Resource
    ScolariteProxy scolariteProxy;

    @GetMapping("/etudiant/{id}")
    Etudiant getEtudiantWithScolarite(@PathVariable("id") Long id)
    {
        Etudiant existEtudiant = etudiantRepository.findById(id).get();
        existEtudiant.setEtudiantScolarite(scolariteProxy.getEtudiantScolarite(id,"tobourse"));
        return existEtudiant;
    }
    @GetMapping("/etudiant/all")
    List<Etudiant> getEtudiantsWithScolarites(){
        List<Etudiant> AllEtudiants= etudiantRepository.findAll();
        AllEtudiants.forEach((e)->{
            e.setEtudiantScolarite(scolariteProxy.getEtudiantScolarite(e.getIdEtudiant() , "tobourse"));
        });
        return AllEtudiants;
    }
}
