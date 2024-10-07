package com.familycard.familycardback.feature.franchise.service;

import com.familycard.familycardback.feature.franchise.dto.request.FranchiseRequestDto;
import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.franchise.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;

    public void addFranchise(FranchiseRequestDto.AddFranchise request) throws Exception{
        Franchise franchise = new Franchise(request);
        franchiseRepository.save(franchise);
    }
}
