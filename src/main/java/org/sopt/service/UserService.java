package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.request.UserCreateRequest;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
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
        if (userCreateRequest.name().length() > 10) {
            throw new CustomException(ErrorMessage.INVALID_USER_NAME);
        }

        User user = userCreateRequest.toUserEntity();
        return userRepository.save(user).getId();
    }
}
