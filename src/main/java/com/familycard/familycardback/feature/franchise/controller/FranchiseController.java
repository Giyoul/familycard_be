package com.familycard.familycardback.feature.franchise.controller;

import com.familycard.familycardback.feature.franchise.dto.request.FranchiseRequestDto;
import com.familycard.familycardback.feature.franchise.dto.response.FranchiseResponseDto;
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

    @PostMapping("/component")
    @Operation(summary = "큐알 리딩 후 이용 메뉴 선택 화면 구성 component 불러오는 기능", description = "찍은 Qr의 Url과 가맹점의 이름을 보내고, 가맹점의 메뉴, 해당 url의 사용자 이름, 금일 사용 횟수, 등급 이름, 등급 할인율, 등급 사용자 하루 최대 사용 가능 횟수를 받아옵니다.")
    public ResponseEntity<?> getComponentFranchises(HttpServletResponse key, @RequestBody FranchiseRequestDto.GetFranchiseComponent request) {
        try {
            FranchiseResponseDto.GetFranchiseComponentResponse response = franchiseService.getFranchiseComponents(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
