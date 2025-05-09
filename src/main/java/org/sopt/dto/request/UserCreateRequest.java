package org.sopt.dto.request;

import org.sopt.domain.User;

public record UserCreateRequest(
        String name,
        String email,
        Integer age
) {
    public User toUserEntity( ) {
        return new User(name, email, age);
    }
}