package com.familycard.familycardback.feature.franchise.controller;

import com.familycard.familycardback.feature.franchise.dto.request.FranchiseRequestDto;
import com.familycard.familycardback.feature.franchise.service.FranchiseService;
import com.familycard.familycardback.feature.user.service.UserService;
import com.familycard.familycardback.global.handler.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/franchise")
public class FranchiseController {
    private final FranchiseService franchiseService;

    @PostMapping("")
    @Operation(summary = "가맹점 추가하기", description = "가맹점 이름과, 가맹점 메뉴 정보를 List로 보내주면 가맹점을 추가해줍니다.")
    public ResponseEntity<?> AddFranchise(@RequestBody FranchiseRequestDto.AddFranchise request, HttpServletResponse key) {
        try {
            franchiseService.addFranchise(request);
            return ResponseEntity.status(HttpStatus.OK).body("Franchise Create Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("")
    @Operation(summary = "가맹 상태 변동시키기", description = "가맹시킬지 말지 status를 변동시킨다.")
    public ResponseEntity<?> updateFranchiseStatus(@RequestBody FranchiseRequestDto.changeFranchiseStatus request, HttpServletResponse key) {
        try {
            franchiseService.updateFranchiseStatus(request);
            return ResponseEntity.status(HttpStatus.OK).body("Franchise Status Update Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("")
    @Operation(summary = "가맹점 전체 불러오기", description = "가맹점 전체의 아이디와 이름을 불러옵니다.")
    public ResponseEntity<?> getAllFranchises(HttpServletResponse key) {
        try {
            List<?> response = franchiseService.getAllFranchise();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
