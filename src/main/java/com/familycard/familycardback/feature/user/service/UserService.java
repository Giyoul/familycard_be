package com.familycard.familycardback.feature.user.service;

import com.familycard.familycardback.feature.membership.entity.Membership;
import com.familycard.familycardback.feature.membership.repository.MembershipRepository;
import com.familycard.familycardback.feature.user.dto.request.UserRequestDto;
import com.familycard.familycardback.feature.user.dto.response.UserResponseDto;
import com.familycard.familycardback.feature.user.entity.User;
import com.familycard.familycardback.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    public List<UserResponseDto.findUserByPageIdInShort> findUserByPageIdInShort(int page_id) {
        int pageSize = 20; // 페이지당 20명씩
        Pageable pageable = PageRequest.of(page_id - 1, pageSize, Sort.by("issueDate").descending());
        List<User> userList = userRepository.findByIssueDateIsNotNullOrderByIssueDateDesc(pageable);
        return userList.stream()
                .map(UserResponseDto.findUserByPageIdInShort::new)
                .toList();
    }

    public void updateUserBySerialNumber(UserRequestDto.UpdateUserRequest request) throws Exception{
        request.getRequestInfoList().forEach(updateUserRequestInfo -> {
            // 시리얼 넘버로 User 객체 조회
            Optional<User> optionalUser = userRepository.findBySerialNumber(updateUserRequestInfo.getSerialNumber());
            Optional<Membership> membership = membershipRepository.findByMembershipName(updateUserRequestInfo.getMembershipName());
            User user;

            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                user = new User();
            }

            if (membership.isPresent()) {
                user.updateUserInfo(updateUserRequestInfo, membership.get());
            } else {
                user.updateUserInfo(updateUserRequestInfo, null);
            }
            userRepository.save(user);
        });
    }

    public List<UserResponseDto.findUserByPageId> findUserByPageId(int page_id) {
        int page = page_id - 1;
        Pageable pageable = PageRequest.of(page, 4);
        List<User> userList = userRepository.findByOrderByIdDesc(pageable);
        return userList.stream()
                .map(UserResponseDto.findUserByPageId::new)
                .toList();
    }

    public List<UserResponseDto.makeUserCard> findUserBySerialList(UserRequestDto.serialNumberList serialNumberList) {
        List<UserResponseDto.makeUserCard> responseDtoList = new ArrayList<>();
        for(String serialNumber : serialNumberList.getSerialNumberList()) {
            Optional<User> optionalUser = userRepository.findBySerialNumber(serialNumber);
            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                responseDtoList.add(new UserResponseDto.makeUserCard(user.getName(), user.getBirthDay(), user.getQRURL()));
            }
        }
        return responseDtoList;
    }
}
