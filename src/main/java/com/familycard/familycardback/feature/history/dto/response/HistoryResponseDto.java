package com.familycard.familycardback.feature.history.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class HistoryResponseDto {
    @Getter
    @Setter
    public static class HistoryResponse{
        String name;
        Date historyDate;
        String membershipName;

        public HistoryResponse(String name, Date historyDate, String membershipName) {
            this.name = name;
            this.historyDate = historyDate;
            this.membershipName = membershipName;
        }
    }

}
