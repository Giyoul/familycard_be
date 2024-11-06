package com.familycard.familycardback.feature.menu.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MenuRequestDto {
    @Getter
    @Setter
    public static class MenuInfo{
        private String menuName;
        private String menuPrice;
        private Boolean menuEnable;
    }

    @Getter
    @Setter
    public static class DeleteOrAddMenuInfo{
        private String franchiseName;
        private List<MenuInfo> menuList;
    }
}
