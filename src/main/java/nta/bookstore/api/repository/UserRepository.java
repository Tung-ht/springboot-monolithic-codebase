package nta.bookstore.api.repository;

import nta.bookstore.api.common.enumtype.EStatus;
import nta.bookstore.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // maybe there are several accounts with the same email, but only 1 account is activated
    List<UserEntity> findAllByEmailAndStatus(String email, EStatus status);

    List<UserEntity> findAllByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email = :email " +
            "ORDER BY created_at DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<UserEntity> findLatestCreatedAccountByMail(String email);
}
