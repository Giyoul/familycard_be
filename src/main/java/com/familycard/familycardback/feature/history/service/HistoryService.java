package com.familycard.familycardback.feature.history.service;

import com.familycard.familycardback.feature.franchise.dto.response.FranchiseResponseDto;
import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.franchise.repository.FranchiseRepository;
import com.familycard.familycardback.feature.history.dto.request.HistoryRequestDto;
import com.familycard.familycardback.feature.history.dto.response.HistoryResponseDto;
import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.history.repository.HistoryRepository;
import com.familycard.familycardback.feature.menu.dto.response.MenuResponseDto;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.familycard.familycardback.feature.menu.repository.MenuRepository;
import com.familycard.familycardback.feature.user.entity.User;
import com.familycard.familycardback.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final FranchiseRepository franchiseRepository;
    private final MenuRepository menuRepository;

    public void addHistory(HistoryRequestDto.addHistory request) throws Exception{
        Optional<User> user = userRepository.findByName(request.getName());
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(request.getFranchiseName());
        Optional<Menu> menu = menuRepository.findByMenuName(request.getMenuName());
        if (user.isPresent() && franchise.isPresent() && menu.isPresent()) {
            History history = new History(franchise.get(), user.get(), menu.get());
            historyRepository.save(history);
        } else {
            throw new Exception("There is such name user or franchise or menu!");
        }
    }

    public FranchiseResponseDto.GetFranchiseComponentResponse getHistory(String userName, String franchiseName) throws Exception {
        Optional<User> user = userRepository.findByName(userName);
        if (!user.get().getIsActive()){
            throw new Exception("맴버십에 등록되어 있지 않은 사용자입니다!");
        }
        if (user.isPresent()) {
            // 오늘 날짜를 Date 형식으로 가져오기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date today = sdf.parse(sdf.format(new Date()));  // 시간을 제외하고 오늘 날짜만 남김

            // historyDate가 오늘 날짜와 같은지 비교
            java.util.List<History> todaysHistories = user.get().getHistoryList().stream()
                    .filter(history -> sdf.format(history.getHistoryDate()).equals(sdf.format(today)))
                    .toList();

            int todayUsageCount = todaysHistories.size();
            List<MenuResponseDto.franchiseMenuComponent> menuDtoList = todaysHistories.stream().map(
                    history -> new MenuResponseDto.franchiseMenuComponent(history.getHistoryMenu(), menuRepository.findById(history.getHistoryMenuId()).get().getMenuPrice())
            ).toList();
            return new FranchiseResponseDto.GetFranchiseComponentResponse(user.get(), todayUsageCount, menuDtoList);
        } else {
            throw new Exception("Invalid QR!");
        }
    }

    public List<HistoryResponseDto.HistoryResponse> getHistoryToday(int page_id, String franchiseName) {
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(franchiseName);
        int count = 0;
        if (franchise.isPresent()) {
            List<History> historyList = franchise.get().getHistoryList();

            LocalDate today = LocalDate.now();
            int skipCount = (page_id - 1) * 20;

            List<HistoryResponseDto.HistoryResponse> responseList = new ArrayList<>();
            for (int i = historyList.size() - 1; i >= 0; i--) {  // 리스트의 마지막 원소부터 탐색
                History history = historyList.get(i);
                LocalDate historyDate = history.getHistoryDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                if (historyDate.equals(today)) {
                    if (count >= skipCount) {
                        responseList.add(new HistoryResponseDto.HistoryResponse(
                                history.getUser().getName(),
                                history.getHistoryDate(),
                                history.getUser().getMembership().getMembershipName()
                        ));
                        if (responseList.size() == 20) {
                            break; // 원하는 페이지 크기만큼 채우면 루프 종료
                        }
                    }
                    count++;
                }
            }
            return responseList;
        }
        return Collections.emptyList();
    }

    public List<HistoryResponseDto.HistoryResponse> getHistoryMonth(int page_id, String franchiseName) {
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(franchiseName);
        int count = 0;
        if (franchise.isPresent()) {
            List<History> historyList = franchise.get().getHistoryList();

            LocalDate today = LocalDate.now();
            int currentYear = today.getYear();
            int currentMonth = today.getMonthValue();
            int skipCount = (page_id - 1) * 20;

            List<HistoryResponseDto.HistoryResponse> responseList = new ArrayList<>();
            for (int i = historyList.size() - 1; i >= 0; i--) {  // 리스트의 마지막 원소부터 탐색
                History history = historyList.get(i);
                LocalDate historyDate = history.getHistoryDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                if (historyDate.getYear() == currentYear && historyDate.getMonthValue() == currentMonth) {
                    if (count >= skipCount) {
                        responseList.add(new HistoryResponseDto.HistoryResponse(
                                history.getUser().getName(),
                                history.getHistoryDate(),
                                history.getUser().getMembership().getMembershipName()
                        ));
                        if (responseList.size() == 20) {
                            break; // 원하는 페이지 크기만큼 채우면 루프 종료
                        }
                    }
                    count++;
                }
            }
            return responseList;
        }
        return Collections.emptyList();
    }


    public List<HistoryResponseDto.HistoryResponse> getHistoryWhole(int page_id, String franchiseName) {
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(franchiseName);
        int count = 0;
        if (franchise.isPresent()) {
            List<History> historyList = franchise.get().getHistoryList();

            int skipCount = (page_id - 1) * 20;

            List<HistoryResponseDto.HistoryResponse> responseList = new ArrayList<>();
            for (int i = historyList.size() - 1; i >= 0; i--) {  // 리스트의 마지막 원소부터 탐색
                History history = historyList.get(i);
                if (count >= skipCount) {
                    responseList.add(new HistoryResponseDto.HistoryResponse(
                            history.getUser().getName(),
                            history.getHistoryDate(),
                            history.getUser().getMembership().getMembershipName()
                    ));
                    if (responseList.size() == 20) {
                        break; // 원하는 페이지 크기만큼 채우면 루프 종료
                    }
                }
                count++;
            }
            return responseList;
        }
        return Collections.emptyList();
    }

    public List<HistoryResponseDto.HistoryResponse> getFranchiseHistory(String franchiseName) {
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(franchiseName);
        if (franchise.isPresent()) {
            List<History> historyList = franchise.get().getHistoryList();

            return historyList.stream().map(
                    history -> new HistoryResponseDto.HistoryResponse(
                            history.getUser().getName(),
                            history.getHistoryDate(),
                            history.getUser().getMembership().getMembershipName()
                    )
            ).toList();
        }
        return Collections.emptyList();
    }

}
