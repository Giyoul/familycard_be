package com.familycard.familycardback.feature.ncmn.entity;

import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ncmn")
public class Ncmn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
     private int id;

    @OneToMany(mappedBy = "ncmn", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Franchise> franchiseList;
}
