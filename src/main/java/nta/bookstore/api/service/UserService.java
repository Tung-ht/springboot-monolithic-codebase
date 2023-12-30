package nta.bookstore.api.service;

import nta.bookstore.api.common.enumtype.EUserAction;
import nta.bookstore.api.dto.UserDto;
import nta.bookstore.api.security.AuthUserDetails;

public interface UserService {
    UserDto.RegistrationResp registration(UserDto.RegistrationReq registration);

    UserDto.LoginResp login(UserDto.LoginReq login);

    UserDto currentUser(AuthUserDetails authUserDetails);

    String requestVerify(EUserAction action, UserDto.RequestOTP requestOTP);

    void sendOTP(EUserAction action, String email);
}
