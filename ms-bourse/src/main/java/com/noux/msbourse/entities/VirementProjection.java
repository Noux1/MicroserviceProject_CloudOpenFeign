package com.noux.msbourse.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "to-scolarite", types= Virement.class)
public interface VirementProjection {

    public Long getIdVirement();
    public Date getDateVirement();

    @Value("#{target.etudiant.nCompteCCP}")
    public Long getNCompteCCP();
}
