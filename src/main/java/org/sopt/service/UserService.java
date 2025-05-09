package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.request.UserCreateRequest;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserJpaRepository userRepository;

    protected UserService(final UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long save(final UserCreateRequest userCreateRequest) {
        User user = userCreateRequest.toUserEntity(
                userCreateRequest.name(),
                userCreateRequest.email(),
                userCreateRequest.age()
        );
        return userRepository.save(user).getId();
    }
}
