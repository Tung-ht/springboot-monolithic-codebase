package nta.bookstore.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.enumtype.EUserAction;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.UserDto;
import nta.bookstore.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public AppResponse<UserDto.RegistrationResp> registration(@Valid @RequestBody UserDto.RegistrationReq registration) {
        return AppResponse.ok(userService.registration(registration));
    }

    @Operation(summary = "api login, return access_token")
    @PostMapping("/login")
    public AppResponse<UserDto.LoginResp> login(@Valid @RequestBody UserDto.LoginReq login) {
        return AppResponse.ok(userService.login(login));
    }

    @Operation(summary = "api verify OTP for REGISTRATION and RESET_PASSWORD")
    @PostMapping("/verify")
    public AppResponse<String> verifyOTP(@RequestParam(value = "action") EUserAction action, @RequestBody UserDto.RequestOTP registrationOTP) {
        return AppResponse.ok(userService.requestVerify(action, registrationOTP));
    }

    @Operation(summary = "api send OTP for REGISTRATION and RESET_PASSWORD")
    @PostMapping("/send-otp")
    public AppResponse<?> sendOTP(@RequestParam(value = "action") EUserAction action, @RequestParam(value = "email") String email) {
        userService.sendOTP(action, email);
        return AppResponse.ok();
    }
}
