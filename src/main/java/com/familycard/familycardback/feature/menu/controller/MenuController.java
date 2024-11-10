package com.familycard.familycardback.feature.menu.controller;

import com.familycard.familycardback.feature.menu.dto.request.MenuRequestDto;
import com.familycard.familycardback.feature.menu.dto.response.MenuResponseDto;
import com.familycard.familycardback.feature.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {
    public final MenuService menuService;

    @PostMapping("")
    @Operation(summary = "메뉴 정보를 넘겨주고, 해당 정보의 메뉴를 추가/ 삭제 합니다.", description = "메뉴 정보에는 메뉴 이름, 메뉴 가격, 그리고 메뉴 사용 유무(true : 메뉴 추가, false : 메뉴 삭제)가 들어갑니다.")
    ResponseEntity<?> addOrDeleteMenu(@RequestBody MenuRequestDto.DeleteOrAddMenuInfo request, HttpServletResponse key) {
        try {
            menuService.addOrDeleteMenu(request);
            return ResponseEntity.status(HttpStatus.OK).body("Menu add or delete success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("")
    @Operation(summary = "프렌차이즈 이름으로 메뉴 전체 가져오기", description = "프렌차이즈 이름을 보내고, 메뉴를 전부 가져옵니다.")
    ResponseEntity<?> getAllMenus(@RequestParam String franchiseName, HttpServletResponse key) {
        try {
            List<MenuResponseDto.franchiseMenuComponent> response = menuService.getAllMenus(franchiseName);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
