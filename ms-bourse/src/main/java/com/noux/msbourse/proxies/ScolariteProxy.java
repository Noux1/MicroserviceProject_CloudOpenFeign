package com.noux.msbourse.proxies;

import com.noux.msbourse.models.EtudiantScolarite;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-scolarite", url="localhost:8082")
public interface ScolariteProxy {

    @GetMapping("/etudiants/{id}")
    EtudiantScolarite getEtudiantScolarite(@PathVariable("id") Long id,
                                           @RequestParam("projection") String projection);
}
