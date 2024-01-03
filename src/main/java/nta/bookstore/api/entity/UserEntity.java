package nta.bookstore.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import nta.bookstore.api.common.enumtype.ERole;
import nta.bookstore.api.common.enumtype.EStatus;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    private String fullName;
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String address;

    // register OTP to validate email
    private String otp;
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Enumerated(EnumType.STRING)
    private ERole role;
}
