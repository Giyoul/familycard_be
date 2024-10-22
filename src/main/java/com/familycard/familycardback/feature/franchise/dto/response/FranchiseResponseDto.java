package com.familycard.familycardback.feature.franchise.dto.response;

import com.familycard.familycardback.feature.menu.dto.response.MenuResponseDto;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.familycard.familycardback.feature.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FranchiseResponseDto {
    @Getter
    @Setter
    public static class GetFranchiseResponse {
        private int franchise_id;
        private String franchiseName;

        public GetFranchiseResponse(int franchise_id, String franchiseName) {
            this.franchise_id = franchise_id;
            this.franchiseName = franchiseName;
        }
    }

    @Getter
    @Setter
    public static class GetFranchiseComponentResponse{
        private String name;
        private int useageCount;
        private String membershipName;
        private int membershipDiscountPercentage;
        private int membershipDiscountCount;
        private List<MenuResponseDto.franchiseMenuComponent> menuList;

        public GetFranchiseComponentResponse(User user, int useageCount, List<MenuResponseDto.franchiseMenuComponent> menuList) {
            this.name = user.getName();
            this.useageCount = useageCount;
            this.menuList = menuList;
            this.membershipName = user.getMembership().getMembershipName();
            this.membershipDiscountPercentage = user.getMembership().getMembershipDiscountPercentage();
            this.membershipDiscountCount = user.getMembership().getMembershipDiscountCount();
        }
    }
}
