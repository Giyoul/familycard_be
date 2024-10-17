package com.familycard.familycardback.feature.user.repository;

import com.familycard.familycardback.feature.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findBySerialNumber(String serialNumber);
    List<User> findByOrderByIdDesc(Pageable pageable);
    List<User> findByIssueDateIsNotNullOrderByIssueDateDesc(Pageable pageable);
    Optional<User> findByQRURL(String qrUrl);
    Optional<User> findByName(String name);
}
