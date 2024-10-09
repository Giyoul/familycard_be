package com.familycard.familycardback.feature.franchise.repository;

import com.familycard.familycardback.feature.franchise.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
    Optional<Franchise> findByFranchiseName(String franchiseName);
}
