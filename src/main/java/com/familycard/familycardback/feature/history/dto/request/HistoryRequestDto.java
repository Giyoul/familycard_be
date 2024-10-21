package com.familycard.familycardback.feature.history.dto.request;

import lombok.Getter;
import lombok.Setter;

public class HistoryRequestDto {
    @Getter
    @Setter
    public static class addHistory{
        private String name;
        private String menuName;
        private String franchiseName;
    }
}
