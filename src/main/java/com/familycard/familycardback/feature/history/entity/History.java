package com.familycard.familycardback.feature.history.entity;

import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.familycard.familycardback.feature.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

@Entity
@Getter
@Setter
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

    @Column(insertable = true, updatable = true, nullable = false)
    private int historyMenuId;

    private Date historyDate;

    private String historyMenu;

    private int historyDiscountPrice;

    private int historyDiscountPercentage;

    public History(Franchise franchise, User user, Menu menu) throws ParseException {
        this.user = user;
        this.franchise = franchise;
        this.historyDiscountPercentage = user.getMembership().getMembershipDiscountPercentage();
        this.historyMenuId = menu.getId();
        this.historyDate = new Date();
        this.historyMenu = menu.getMenuName();
        NumberFormat format = NumberFormat.getInstance();
        Number number = format.parse(menu.getMenuPrice());
        int intValue = number.intValue(); // 정수형으로 변환
        this.historyDiscountPrice = intValue * user.getMembership().getMembershipDiscountPercentage() / 100;
    }
}
