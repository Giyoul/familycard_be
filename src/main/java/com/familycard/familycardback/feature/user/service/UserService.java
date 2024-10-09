package com.familycard.familycardback.feature.user.service;

import com.familycard.familycardback.feature.membership.entity.Membership;
import com.familycard.familycardback.feature.membership.repository.MembershipRepository;
import com.familycard.familycardback.feature.user.dto.request.UserRequestDto;
import com.familycard.familycardback.feature.user.entity.User;
import com.familycard.familycardback.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    public List<?> findUserByPageId(int page_id) {
        return null;
    }

    public void updateUserBySerialNumber(UserRequestDto.UpdateUserRequest request) throws Exception{
        request.getRequestInfoList().forEach(updateUserRequestInfo -> {
            // 시리얼 넘버로 User 객체 조회
            Optional<User> optionalUser = userRepository.findBySerialNumber(updateUserRequestInfo.getSerialNumber());
            Optional<Membership> membership = membershipRepository.findByMembershipName(updateUserRequestInfo.getMembershipName());
            User user;

            if (optionalUser.isPresent()) {
                System.out.println("유저 있음요~");
                user = optionalUser.get();
            } else {
                System.out.println("하이요~");
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

}
