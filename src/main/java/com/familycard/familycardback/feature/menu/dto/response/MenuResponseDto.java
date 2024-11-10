package com.familycard.familycardback.feature.menu.dto.response;

import lombok.Getter;
import lombok.Setter;

public class MenuResponseDto {
    @Getter
    @Setter
    public static class franchiseMenuComponent{
        private String menuName;
        private String menuPrice;
        private boolean menuEnable;

        public franchiseMenuComponent(String menuName, String menuPrice, boolean menuEnable) {
            this.menuName = menuName;
            this.menuPrice = menuPrice;
            this.menuEnable = menuEnable;
        }
    }
}
