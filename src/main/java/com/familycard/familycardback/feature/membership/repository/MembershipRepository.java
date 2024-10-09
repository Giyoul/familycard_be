package com.familycard.familycardback.feature.membership.repository;

import com.familycard.familycardback.feature.membership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {
    Optional<Membership> findByMembershipName(String membershipName);
}
