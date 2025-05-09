package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.request.UserCreateRequest;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserJpaRepository userRepository;

    protected UserService(final UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long save(final UserCreateRequest userCreateRequest) {
        User user = userCreateRequest.toUserEntity();
        return userRepository.save(user).getId();
    }
}
