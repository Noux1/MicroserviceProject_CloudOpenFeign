package com.noux.msformation;

import com.noux.msformation.entities.Formation;
import com.noux.msformation.repositories.FormationRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@EnableFeignClients
@SpringBootApplication
public class MsFormationApplication implements CommandLineRunner {

    @Resource
    FormationRepository formationRepository;

    @Resource
    RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(MsFormationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Formation.class);

        Formation f1=  formationRepository.save(new Formation(null, "Spring Boot",30,null));
        Formation f2=formationRepository.save(new Formation(null, "reactJs",40,null));

    }
}

