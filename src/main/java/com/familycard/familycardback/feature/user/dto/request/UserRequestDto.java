package com.familycard.familycardback.feature.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class UserRequestDto {
    @Getter
    @Setter
    public static class UpdateUserRequest {
        private List<UpdateUserRequestInfo> requestInfoList;
    }

    @Getter
    @Setter
    public static class UpdateUserRequestInfo {
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
    }
}
