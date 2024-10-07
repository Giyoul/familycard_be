package com.familycard.familycardback.feature.menu.dto.request;

import lombok.Getter;
import lombok.Setter;

public class MenuRequestDto {
    @Getter
    @Setter
    public static class MenuInfo{
        private String menuName;
        private String menuPrice;
    }
}
