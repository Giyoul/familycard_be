package com.familycard.familycardback.feature.franchise.service;

import com.familycard.familycardback.feature.franchise.dto.request.FranchiseRequestDto;
import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.franchise.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;

    public void addFranchise(FranchiseRequestDto.AddFranchise request) throws Exception{
        Franchise franchise = new Franchise(request);
        franchiseRepository.save(franchise);
    }

    public void updateFranchiseStatus(FranchiseRequestDto.changeFranchiseStatus request) throws Exception{
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(request.getFranchiseName());
        if (franchise.isPresent()) {
            if (franchise.get().getFranchiseAffiliated() == request.isFranchiseAffiliated()) {
                throw new Exception("Request and current franchise status is same!");
            } else {
                franchise.get().changeFranchiseAffiliated(request.isFranchiseAffiliated());
                franchiseRepository.save(franchise.get());
            }
        } else {
            throw new Exception("There is no franchise with this name");
        }
    }
}
