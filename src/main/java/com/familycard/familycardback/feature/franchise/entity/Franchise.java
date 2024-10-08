package com.familycard.familycardback.feature.franchise.entity;

import com.familycard.familycardback.feature.franchise.dto.request.FranchiseRequestDto;
import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Menu> menuList;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<History> historyList;

    private String franchiseName;

    private boolean franchiseAffiliated;

    public Franchise(FranchiseRequestDto.AddFranchise request) {
        this.franchiseName = request.getFranchiseName();
        // 메뉴 리스트를 받아서 각각의 Menu 객체로 변환하여 추가
        this.menuList = request.getMenuList().stream()
                .map(menuInfo -> new Menu(this, menuInfo.getMenuName(), menuInfo.getMenuPrice()))
                .collect(Collectors.toList());
        this.franchiseAffiliated = true;
    }
}
