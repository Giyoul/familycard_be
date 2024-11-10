package com.familycard.familycardback.feature.menu.service;

import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.franchise.repository.FranchiseRepository;
import com.familycard.familycardback.feature.menu.dto.request.MenuRequestDto;
import com.familycard.familycardback.feature.menu.dto.response.MenuResponseDto;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.familycard.familycardback.feature.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final FranchiseRepository franchiseRepository;

    public void addOrDeleteMenu(MenuRequestDto.DeleteOrAddMenuInfo request) throws Exception{
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(request.getFranchiseName());

        if (franchise.isPresent()) {
            // 기존 메뉴를 menuName을 키로 하는 Map으로 변환
            Map<String, Menu> existingMenuMap = franchise.get().getMenuList().stream()
                    .collect(Collectors.toMap(Menu::getMenuName, menu -> menu));

            // 2. 요청된 메뉴를 순회하면서 업데이트 또는 추가
            List<Menu> updatedMenuList = request.getMenuList().stream().map(menuInfo -> {
                Menu menu = existingMenuMap.get(menuInfo.getMenuName());
                if (menu != null) {
                    // 기존 메뉴가 존재하면 정보 업데이트
                    menu.updateMenuInfo(menuInfo.getMenuName(), menuInfo.getMenuPrice(), menuInfo.getMenuEnable());
                    return menu;
                } else {
                    // 기존 메뉴가 없으면 새 메뉴 생성
                    return new Menu(franchise.get(), menuInfo.getMenuName(), menuInfo.getMenuPrice(), menuInfo.getMenuEnable());
                }
            }).collect(Collectors.toList());

            // 3. 모든 변경 사항을 저장
            menuRepository.saveAll(updatedMenuList);
        } else {
            throw new Exception("There is no such franchise!");
        }
    }

    public List<MenuResponseDto.franchiseMenuComponent> getAllMenus(String franchiseName) throws Exception{
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(franchiseName);

        if (franchise.isPresent()) {
            List<Menu> menuList = franchise.get().getMenuList();
            return menuList.stream().map(menu ->
                    new MenuResponseDto.franchiseMenuComponent(menu.getMenuName(), menu.getMenuPrice(), menu.getMenuEnable())
            ).toList();
        } else {
            throw new Exception("There is no such franchise!");
        }
    }
}
