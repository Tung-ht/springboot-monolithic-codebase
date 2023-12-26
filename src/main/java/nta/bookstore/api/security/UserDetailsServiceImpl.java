package nta.bookstore.api.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nta.bookstore.api.common.enumtype.EStatus;
import nta.bookstore.api.entity.UserEntity;
import nta.bookstore.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<UserEntity> users = userRepository.findAllByEmailAndStatus(email, EStatus.ACTIVE);
        if (!users.isEmpty()) {
            UserEntity user = users.get(0);
            return AuthUserDetails.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .authority(user.getRole())
                    .build();
        } else {
            return null;
        }
    }
}
