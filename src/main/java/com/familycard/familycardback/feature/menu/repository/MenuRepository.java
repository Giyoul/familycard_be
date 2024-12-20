package com.familycard.familycardback.feature.menu.repository;

import com.familycard.familycardback.feature.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Optional<Menu> findByMenuName(String menuName);
}
