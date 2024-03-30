package com.noux.msbourse;

import com.noux.msbourse.entities.Etudiant;
import com.noux.msbourse.entities.Virement;
import com.noux.msbourse.repositories.EtudiantRepository;
import com.noux.msbourse.repositories.VirementRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.sql.Date;

@SpringBootApplication
@EnableFeignClients
public class MsBourseApplication  implements CommandLineRunner {

    @Resource
    EtudiantRepository etudiantRepository;

    @Resource
    VirementRepository virementRepository;
    public static void main(String[] args) {
        SpringApplication.run(MsBourseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Etudiant e1= etudiantRepository.save(new Etudiant(1L,null,40000F,true,null,null));
        Etudiant e2=etudiantRepository.save(new Etudiant(2L,null,20000F,true,null,null));
        Etudiant e3=	etudiantRepository.save(new Etudiant(3L,null,90000F,true,null,null));
        Etudiant e4=	etudiantRepository.save(new Etudiant(4L,null,60000F,true,null,null));

        virementRepository.save(new Virement(null,4000F, Date.valueOf("2023-05-13"),e1));
        virementRepository.save(new Virement(null,4000F, Date.valueOf("2023-05-13"),e1));
        virementRepository.save(new Virement(null,8000F, Date.valueOf("2023-05-13"),e1));

        virementRepository.save(new Virement(null,8000F, Date.valueOf("2023-02-01"),e2));
        virementRepository.save(new Virement(null,12000F, Date.valueOf("2023-05-01"),e2));

        virementRepository.save(new Virement(null,5000F, Date.valueOf("2020-06-01"),e3));
        virementRepository.save(new Virement(null,9000F, Date.valueOf("2019-07-01"),e3));
    }
}

