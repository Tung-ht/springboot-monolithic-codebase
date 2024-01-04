package nta.bookstore.api.service.impl;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.constant.CommonConst;
import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.common.enumtype.ERole;
import nta.bookstore.api.common.enumtype.EStatus;
import nta.bookstore.api.common.enumtype.EUserAction;
import nta.bookstore.api.common.exception.AppException;
import nta.bookstore.api.common.exception.NotFoundException;
import nta.bookstore.api.common.mapper.UserMapper;
import nta.bookstore.api.common.utils.MailSender;
import nta.bookstore.api.dto.UserDto;
import nta.bookstore.api.entity.UserEntity;
import nta.bookstore.api.repository.UserRepository;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.security.JwtUtils;
import nta.bookstore.api.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;
    private final UserMapper userMapper;

    @Override
    public UserDto.RegistrationResp registration(UserDto.RegistrationReq registration) {
        // maybe there are several accounts with the same email, but only 1 account is activated
        userRepository.findAllByEmailAndStatus(registration.getEmail(), EStatus.ACTIVE)
                .stream()
                .findAny()
                .ifPresent(entity -> {throw new AppException(ResponseConst.DUPLICATE_CODE, ResponseConst.EMAIL_ALREADY_USED);});

        UserEntity userEntity = UserEntity.builder()
                .email(registration.getEmail())
                .password(passwordEncoder.encode(registration.getPassword()))
                .fullName(registration.getFullName())
                .status(EStatus.INACTIVE)
//                .status(EStatus.ACTIVE)
                .role(ERole.ROLE_USER)
                .build();
        userEntity = userRepository.save(userEntity);
        mailSender.sendMail(userEntity.getEmail(), EUserAction.VERIFY_EMAIL);
        return UserDto.RegistrationResp.builder().email(userEntity.getEmail()).build();
    }

    @Override
    public UserDto.LoginResp login(UserDto.LoginReq login) {
        UserEntity userEntity = userRepository.findAllByEmailAndStatus(login.getEmail(), EStatus.ACTIVE)
                .stream()
                .filter(user -> passwordEncoder.matches(login.getPassword(), user.getPassword()))
                .findFirst()
                .orElseThrow(() -> new AppException(ResponseConst.AUTHENTICATION_FAIL_CODE, ResponseConst.AUTHENTICATION_FAIL));
        String jwt = jwtUtils.encode(userEntity.getEmail());

        return UserDto.LoginResp.builder()
                .accessToken(jwt)
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .role(userEntity.getRole())
                .build();
    }

    @Override
    public UserDto currentUser(AuthUserDetails authUserDetails) {
        UserEntity userEntity = userRepository.findById(authUserDetails.getId()).orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));
        return userMapper.toDto(userEntity);
    }

    @Override
    public String requestVerify(EUserAction action, UserDto.RequestOTP requestOTP) {
        // maybe there are several accounts with the same email, but only the latest account will be activated
        var user = userRepository.findLatestCreatedAccountByMail(requestOTP.getEmail())
                .orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));
        var otp = user.getOtp();
        // if otp is null -> exception is caught -> Error
        if (!otp.equals(requestOTP.getOtp())) {
            throw new AppException(ResponseConst.INVALID_DATA_CODE, ResponseConst.WRONG_OTP);
        } else {
            if (action.equals(EUserAction.VERIFY_EMAIL)) {
                user.setStatus(EStatus.ACTIVE);
                userRepository.save(user);
                return CommonConst.REGISTRATION_SUCCESS_MSG;
            }
            // if RESET_PASSWORD -> return 200
            return CommonConst.RESET_PASSWORD_SUCCESS_MSG;
        }
    }

    @Override
    public void sendOTP(EUserAction action, String email) {
        var users = userRepository.findAllByEmail(email);
        if (users.isEmpty()) {
            throw new NotFoundException(ResponseConst.USER_NOT_FOUND);
        }
        mailSender.sendMail(email, action);
    }

    @Override
    public Page<UserDto> getUsersDashboard(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }
}
