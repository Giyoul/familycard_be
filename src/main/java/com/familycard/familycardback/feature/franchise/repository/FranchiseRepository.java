package com.familycard.familycardback.feature.franchise.repository;

import com.familycard.familycardback.feature.franchise.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
}
