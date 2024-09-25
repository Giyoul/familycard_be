package com.familycard.familycardback.feature.user.entity;

import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.membership.entity.Membership;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.familycard.familycardback.feature.ncmn.entity.Ncmn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
    private int id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<History> historyList;

    @ManyToOne
    @JoinColumn(name = "mambership_id")
    private Membership membership;

    private String serialNumber;

    private Date issueDate;

    private int supporterId;

    private String name;

    private String supporterName;

    private String contactNumber;

    private String photoFileName;

    private Date birthDay;

    private String QRURL;
}
