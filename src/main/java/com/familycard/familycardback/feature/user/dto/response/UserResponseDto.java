package com.familycard.familycardback.feature.user.dto.response;

import com.familycard.familycardback.feature.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserResponseDto {

    @Getter
    @Setter
    public static class findUserByPageIdInShort{
        private int id;
        private Date historyDate;
        private String name;
        private String membershipName;
        private String franchiseName;
        private int supporterId;
        private String serialNumber;

        public findUserByPageIdInShort(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.historyDate = user.getIssueDate();
            this.membershipName = user.getMembership().getMembershipName();
            this.franchiseName = user.getLastUsedFranchiseName();
            this.serialNumber = user.getSerialNumber();
            this.supporterId = user.getSupporterId();
        }
    }

    @Getter
    @Setter
    public static class findUserByPageId{
        private int user_id;
        private String serialNumber;
        private Date issueDate;
        private Integer supporterId;
        private String name;
        private String supporterName;
        private String contactNumber;
        private String photoFileName;
        private Date birthDay;
        private String QRURL;
        private String membershipName;

        public findUserByPageId(User user) {
            this.user_id = user.getId();
            this.serialNumber = user.getSerialNumber();
            this.issueDate = user.getIssueDate();
            this.supporterId = user.getSupporterId();
            this.name = user.getName();
            this.supporterName = user.getSupporterName();
            this.contactNumber = user.getContactNumber();
            this.photoFileName = user.getPhotoFileName();
            this.birthDay = user.getBirthDay();
            this.QRURL = user.getQRURL();
            if (user.getMembership() != null) {
                this.membershipName = user.getMembership().getMembershipName();
            } else {
                this.membershipName = "";
            }

        }
    }

    @Getter
    @Setter
    public static class makeUserCard {
        String name;
        Date birthDay;
        String QRURL;

        public makeUserCard(String name, Date birthDay, String QRURL) {
            this.name = name;
            this.birthDay = birthDay;
            this.QRURL = QRURL;
        }
    }
}
