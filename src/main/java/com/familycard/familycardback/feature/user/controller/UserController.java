package com.familycard.familycardback.feature.user.controller;

import com.familycard.familycardback.feature.user.dto.request.UserRequestDto;
import com.familycard.familycardback.feature.user.dto.response.UserResponseDto;
import com.familycard.familycardback.feature.user.repository.UserRepository;
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
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "유저 데이터 페이지 별로 가져오기", description = "페이지 번호와, header에 key값을 보내주면 validation check 후에 해당 페이지의 유저 데이터를 가져옵니다.")
    public ResponseEntity<?> findUserByPage(@RequestParam int page_id, HttpServletResponse key) {
        try {
            List<?> responseDto = userService.findUserByPageIdInShort(page_id);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("")
    @Operation(summary = "후언자 정보 업데이트 & 추가 기능", description = "사용자 정보를 List로 넘겨주면 해당 serialNumber의 사용자가 있으면 해당 정보로 수정해주고, 없으면 추가해줍니다.")
    public ResponseEntity<?> updateUserBySerialNumber(@RequestBody UserRequestDto.UpdateUserRequest request, HttpServletResponse key) {
        try {
            userService.updateUserBySerialNumber(request);
            return ResponseEntity.status(HttpStatus.OK).body("Update User info Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/{page_id}")
    @Operation(summary = "페이지 번호로 유저 정보 가져오는 기능", description = "pathVariable로 페이지의 번호를 보내주면 해당 페이지의 user 정보를 list로 가져올 수 있습니다.")
    public ResponseEntity<?> getUserByPageId(@PathVariable int page_id, HttpServletResponse key) {
        try {
            List<UserResponseDto.findUserByPageId> response = userService.findUserByPageId(page_id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
