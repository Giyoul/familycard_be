package com.familycard.familycardback.feature.membership.entity;

import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.user.entity.User;
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
@Table(name = "membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
    private int id;

    @OneToMany(mappedBy = "membership", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    @JsonIgnore
    private List<User> userList;

    private String membershipName;

    private int membershipDiscountPercentage;

    private int membershipDiscountCount;
}
