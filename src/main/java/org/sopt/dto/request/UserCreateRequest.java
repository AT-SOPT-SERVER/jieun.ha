package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.sopt.domain.User;

public record UserCreateRequest(
        @NotBlank(message = "닉네임은 필수입니다.") String name,
        String email,
        Integer age
) {
    public static User toEntity(UserCreateRequest request) {
        return new User(request.name(), request.email(), request.age());
    }
}