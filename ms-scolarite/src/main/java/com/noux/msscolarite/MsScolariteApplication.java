package com.noux.msscolarite;

import com.noux.msscolarite.entities.Etablissement;
import com.noux.msscolarite.entities.Etudiant;
import com.noux.msscolarite.repositories.EtablissementRepository;
import com.noux.msscolarite.repositories.EtudiantRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.sql.Date;

@SpringBootApplication
@EnableFeignClients
public class MsScolariteApplication implements CommandLineRunner {

    @Resource
    EtablissementRepository etablissementRepository;

    @Resource
    EtudiantRepository etudiantRepository;
    public static void main(String[] args) {
        SpringApplication.run(MsScolariteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Etablissement e1=etablissementRepository.save(new Etablissement(null,"esi-sba",null));
        Etablissement e2=etablissementRepository.save(new Etablissement(null,"univ-sba",null));

        etudiantRepository.save(new Etudiant(null,"nour", Date.valueOf("2001-09-03"),"4siw",null,e1,1L,null,null));
        etudiantRepository.save(new Etudiant(null,"malki", Date.valueOf("1995-08-11"),"4siw",null,e1,2L,null,null));
        etudiantRepository.save(new Etudiant(null,"maya", Date.valueOf("2014-11-22"),"4isi",null,e2,1L,null,null));
        etudiantRepository.save(new Etudiant(null,"samir", Date.valueOf("2009-02-15"),"4isi",null,e2,1L,null,null));

    }
}

