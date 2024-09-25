package com.familycard.familycardback.feature.franchise.entity;

import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.menu.entity.Menu;
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
@Table(name = "franchise")
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ncmn_id")
    private Ncmn ncmn;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Menu> menuList;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<History> historyList;

    private String franchiseName;
}
