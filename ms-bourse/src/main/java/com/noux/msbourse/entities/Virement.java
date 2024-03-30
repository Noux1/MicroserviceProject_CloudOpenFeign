package com.noux.msbourse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVirement;

    private Float montant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVirement;

    @ManyToOne
    private Etudiant etudiant;
}
