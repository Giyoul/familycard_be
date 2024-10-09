package com.familycard.familycardback.feature.user.entity;

import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.membership.entity.Membership;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.familycard.familycardback.feature.user.dto.request.UserRequestDto;
import com.familycard.familycardback.feature.user.repository.UserRepository;
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
    @JoinColumn(name = "membership_id", nullable = true)
    private Membership membership;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    private Date issueDate;

    private int supporterId;

    private String name;

    private String supporterName;

    private String contactNumber;

    private String photoFileName;

    private Date birthDay;

    private String QRURL;

    public void updateUserInfo(UserRequestDto.UpdateUserRequestInfo request, Membership membership) {
        this.serialNumber = request.getSerialNumber();
        this.issueDate = request.getIssueDate();
        this.supporterId = request.getSupporterId();
        this.name = request.getName();
        this.supporterName = request.getSupporterName();
        this.contactNumber = request.getContactNumber();
        this.photoFileName = request.getPhotoFileName();
        this.birthDay = request.getBirthDay();
        this.QRURL = request.getQRURL();
        this.membership = membership;
    }
}
