package com.familycard.familycardback.feature.ncmn.repository;

import com.familycard.familycardback.feature.ncmn.entity.Ncmn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NcmnRepository extends JpaRepository<Ncmn, Integer> {
}
