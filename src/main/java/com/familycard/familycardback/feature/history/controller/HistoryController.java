package com.familycard.familycardback.feature.history.controller;

import com.familycard.familycardback.feature.franchise.dto.response.FranchiseResponseDto;
import com.familycard.familycardback.feature.history.dto.request.HistoryRequestDto;
import com.familycard.familycardback.feature.history.dto.response.HistoryResponseDto;
import com.familycard.familycardback.feature.history.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping("")
    @Operation(summary = "먹을 메뉴 선택 후 먹었다고 보내는 기능", description = "사용자 이름과, 먹은 메뉴 이름, 프렌차이즈 이름을 보내주면, 쿠폰을 사용함.")
    public ResponseEntity<?> AddHistory(@RequestBody HistoryRequestDto.addHistory request, HttpServletResponse key) {
        try {
            historyService.addHistory(request);
            return ResponseEntity.status(HttpStatus.OK).body("History Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/user")
    @Operation(summary = "큐알 리딩 후에 메뉴 선택 했을 때 뜨는 화면", description = "사용자의 이름과 프렌타이즈 이름을 보내고, 유저가 오늘 해당 프렌차이즈에서 먹은 메뉴를 가져옵니다.")
    public ResponseEntity<?> getHistory(HttpServletResponse key, @RequestParam String userName, @RequestParam String franchiseName) {
        try {
            FranchiseResponseDto.GetFranchiseComponentResponse response = historyService.getHistory(userName, franchiseName);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/today/{page_id}")
    @Operation(summary = "페이지 번호별로 오늘의 history 내역 가져오기", description = "page id와 프렌차이즈 이름을 보내주면, 해당 프렌차이즈의 page 번호의 history 정보를 가져옵니다.")
    public ResponseEntity<?> getHistoryToday(@PathVariable int page_id, @RequestParam String franchiseName, HttpServletResponse key) {
        try {
            List<HistoryResponseDto.HistoryResponse> response = historyService.getHistoryToday(page_id, franchiseName);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/month/{page_id}")
    @Operation(summary = "페이지 번호별로 오늘의 history 내역 가져오기", description = "page id와 프렌차이즈 이름을 보내주면, 해당 프렌차이즈의 page 번호의 history 정보를 가져옵니다.")
    public ResponseEntity<?> getHistoryMonth(@PathVariable int page_id, @RequestParam String franchiseName, HttpServletResponse key) {
        try {
            List<HistoryResponseDto.HistoryResponse> response = historyService.getHistoryMonth(page_id, franchiseName);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
