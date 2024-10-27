package com.familycard.familycardback.feature.history.repository;

import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    long countByHistoryDateAndUser(Date date, User user);
}
