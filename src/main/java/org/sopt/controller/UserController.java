package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.dto.base.BaseResponse;
import org.sopt.dto.request.UserCreateRequest;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    protected UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public BaseResponse<Long> registerUser(
            @Valid @RequestBody UserCreateRequest userCreateRequest
    ) {
        return BaseResponse.success(SuccessMessage.CREATED, userService.save(userCreateRequest));
    }
}
