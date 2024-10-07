package com.familycard.familycardback.feature.user.service;

import com.familycard.familycardback.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public static List<?> findUserByPageId(int page_id) {
        return null;
    }
}
