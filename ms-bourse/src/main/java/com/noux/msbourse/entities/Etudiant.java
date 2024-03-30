package com.noux.msbourse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.noux.msbourse.models.EtudiantScolarite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;

    private Long nCompteCCP;

    private Float salaireParant;

    private Boolean situationImpot;

    @JsonIgnore
    @OneToMany(mappedBy = "etudiant")
    List<Virement> virements;

    @Transient
    EtudiantScolarite etudiantScolarite;
}
