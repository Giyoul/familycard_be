package com.familycard.familycardback.feature.user.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class userResponseDto {

    @Getter
    @Setter
    public static class findUserByPageId{
        private int id;
        private Date historyDate;
        private String name;
        private String membershipName;
        private int supporterId;
        private String serialNumber;


    }

}
