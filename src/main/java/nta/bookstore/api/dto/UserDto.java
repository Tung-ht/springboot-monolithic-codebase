package nta.bookstore.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ERole;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String address;

    @Getter
    @Setter
    @Builder
    public static class LoginReq {
        @NotBlank(message = "email is required!")
        private String email;
        @NotBlank(message = "password is required!")
        private String password;
        private ERole displayRole;
    }

    @Getter
    @Setter
    @Builder
    public static class LoginResp {
        private String fullName;
        private ERole role;
        private String accessToken;
    }

    @Getter
    @Setter
    @Builder
    public static class RegistrationReq {
        @NotBlank
        private String fullName;
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }

    @Getter
    @Setter
    @Builder
    public static class RegistrationResp {
        private String email;
    }

    @Getter
    @Setter
    @Builder
    public static class Update {
        private String fullName;
        private String phone;
        private String address;
    }

    @Getter
    @Setter
    @Builder
    public static class RequestOTP {
        private String email;
        private String otp;
    }
}
