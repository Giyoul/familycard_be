package com.familycard.familycardback.feature.user.controller;

import com.familycard.familycardback.feature.user.repository.UserRepository;
import com.familycard.familycardback.feature.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> findUserByPage(@RequestParam int page_id, HttpServletResponse key) {
        List<?> responseDto = UserService.findUserByPageId(page_id);
        return ResponseEntity.status().body(responseDto);
    }
}
