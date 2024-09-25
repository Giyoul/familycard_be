package com.familycard.familycardback.feature.history.entity;

import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
    private int id;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int menu_id;

    private Date historyDate;

    private String historyMenu;

    private String historyDiscountPrice;

    private String historyDiscountPercentage;

}
