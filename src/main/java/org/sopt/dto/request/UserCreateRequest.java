package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.sopt.domain.User;

public record UserCreateRequest(
        @NotBlank(message = "닉네임은 필수입니다.") String name,
        String email,
        Integer age
) {
    public User toUserEntity( ) {
        return new User(name, email, age);
    }
}