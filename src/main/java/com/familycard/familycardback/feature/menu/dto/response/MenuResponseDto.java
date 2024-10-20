package com.familycard.familycardback.feature.menu.dto.response;

import lombok.Getter;
import lombok.Setter;

public class MenuResponseDto {
    @Getter
    @Setter
    public static class franchiseMenuComponent{
        private String menuName;
        private String menuPrice;

        public franchiseMenuComponent(String menuName, String menuPrice) {
            this.menuName = menuName;
            this.menuPrice = menuPrice;
        }
    }
}
