package nta.bookstore.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ERole;
import nta.bookstore.api.common.enumtype.EStatus;

import javax.persistence.*;

@Getter
@Setter
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
    private String address;

    // register OTP to validate email
    private String otp;
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @Builder
    public UserEntity(String email, String password, String fullName, String phone, String address, String otp, EStatus status, ERole role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.otp = otp;
        this.status = status;
        this.role = role;
    }
}
