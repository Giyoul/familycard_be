package com.familycard.familycardback.feature.menu.entity;

import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.ncmn.entity.Ncmn;
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
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
    private int id;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    private String menuName;

    private String menuPrice;
}
