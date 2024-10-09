package com.familycard.familycardback.feature.franchise.dto.response;

import lombok.Getter;
import lombok.Setter;

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
}
