package org.sopt.domain.post.validator;

import org.sopt.domain.post.exception.InvalidTitleException;

public class PostValidator {
    public static void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTitleException("제목은 공백이거나 비어 있을 수 없습니다.");
        }
    }
}