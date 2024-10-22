package com.familycard.familycardback.feature.franchise.service;

import com.familycard.familycardback.feature.franchise.dto.request.FranchiseRequestDto;
import com.familycard.familycardback.feature.franchise.dto.response.FranchiseResponseDto;
import com.familycard.familycardback.feature.franchise.entity.Franchise;
import com.familycard.familycardback.feature.franchise.repository.FranchiseRepository;
import com.familycard.familycardback.feature.history.entity.History;
import com.familycard.familycardback.feature.menu.dto.response.MenuResponseDto;
import com.familycard.familycardback.feature.menu.entity.Menu;
import com.familycard.familycardback.feature.user.entity.User;
import com.familycard.familycardback.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;
    private final UserRepository userRepository;

    public void addFranchise(FranchiseRequestDto.AddFranchise request) throws Exception{
        Franchise franchise = new Franchise(request);
        franchiseRepository.save(franchise);
    }

    public void updateFranchiseStatus(FranchiseRequestDto.changeFranchiseStatus request) throws Exception{
        Optional<Franchise> franchise = franchiseRepository.findByFranchiseName(request.getFranchiseName());
        if (franchise.isPresent()) {
            if (franchise.get().getFranchiseAffiliated() == request.isFranchiseAffiliated()) {
                throw new Exception("Request and current franchise status is same!");
            } else {
                franchise.get().changeFranchiseAffiliated(request.isFranchiseAffiliated());
                franchiseRepository.save(franchise.get());
            }
        } else {
            throw new Exception("There is no franchise with this name");
        }
    }

    public List<?> getAllFranchise() throws Exception{
        List<FranchiseResponseDto.GetFranchiseResponse> responses = franchiseRepository.findAll()
                .stream().map(franchise -> new FranchiseResponseDto.GetFranchiseResponse(franchise.getId(), franchise.getFranchiseName())).toList();
        return responses;
    }

    public FranchiseResponseDto.GetFranchiseComponentResponse getFranchiseComponents(FranchiseRequestDto.GetFranchiseComponent request) throws Exception {
        Optional<User> user = userRepository.findByQRURL(request.getQRURL());
        if (!user.get().getIsActive()){
            throw new Exception("맴버십에 등록되어 있지 않은 사용자입니다!");
        }
        if (user.isPresent()) {
            // 오늘 날짜를 Date 형식으로 가져오기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date today = sdf.parse(sdf.format(new Date()));  // 시간을 제외하고 오늘 날짜만 남김

            // historyDate가 오늘 날짜와 같은지 비교
            List<History> todaysHistories = user.get().getHistoryList().stream()
                    .filter(history -> sdf.format(history.getHistoryDate()).equals(sdf.format(today)))
                    .toList();

            int todayUsageCount = todaysHistories.size();
            List<Menu> menuList = franchiseRepository.findByFranchiseName(request.getFranchiseName()).get().getMenuList();
            List<MenuResponseDto.franchiseMenuComponent> menuDtoList = menuList.stream().map(
                    menu -> new MenuResponseDto.franchiseMenuComponent(menu.getMenuName(), menu.getMenuPrice())
            ).toList();
            return new FranchiseResponseDto.GetFranchiseComponentResponse(user.get(), todayUsageCount, menuDtoList);
        } else {
            throw new Exception("Invalid QR!");
        }
    }
}
