package com.familycard.familycardback.feature.franchise.dto.request;

import com.familycard.familycardback.feature.menu.dto.request.MenuRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FranchiseRequestDto {
    @Getter
    @Setter
    public static class AddFranchise{
        private String franchiseName;
        private List<MenuRequestDto.MenuInfo> menuList;
    }

    @Getter
    @Setter
    public static class changeFranchiseStatus{
        private String franchiseName;
        private boolean franchiseAffiliated;
    }
}
