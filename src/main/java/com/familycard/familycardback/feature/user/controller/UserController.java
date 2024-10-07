package com.familycard.familycardback.feature.user.controller;

import com.familycard.familycardback.feature.user.repository.UserRepository;
import com.familycard.familycardback.feature.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    @GetMapping("")
    @Operation(summary = "유저 데이터 페이지 별로 가져오기", description = "페이지 번호와, header에 key값을 보내주면 validation check 후에 해당 페이지의 유저 데이터를 가져옵니다.")
    public ResponseEntity<?> findUserByPage(@RequestParam int page_id, HttpServletResponse key) {
        List<?> responseDto;
        try {
            responseDto = UserService.findUserByPageId(page_id);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
