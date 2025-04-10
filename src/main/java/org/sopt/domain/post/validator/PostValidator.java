package org.sopt.domain.post.validator;

import org.sopt.domain.post.exception.InvalidTitleException;

public class PostValidator {
    private static final int MAX_TITLE_LENGTH = 30;

    public static void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTitleException("제목은 공백이거나 비어 있을 수 없습니다.");
        }

        if (title.length() > MAX_TITLE_LENGTH) {
            throw new InvalidTitleException("제목은 30자 이내여야 합니다.");
        }
    }
}