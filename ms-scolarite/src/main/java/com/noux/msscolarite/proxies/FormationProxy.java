package com.noux.msscolarite.proxies;

import com.noux.msscolarite.models.Formation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-formation", url = "localhost:8081")
public interface FormationProxy {

    @GetMapping("/formations/{id}")

    public Formation getFormation(@PathVariable("id") Long idf);

}
