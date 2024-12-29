package nta.bookstore.api.repository;

import nta.bookstore.api.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByUser_IdOrderByCreatedAtDesc(Long userId);

    Long countByUser_IdAndIsRead(Long userId, Boolean isRead);

    @Modifying
    @Query(value = "UPDATE NotificationEntity n " +
            "SET n.isRead = true " +
            "WHERE n.user.id = :userId")
    void readAllByToUserId(Long userId);

    @Modifying
    @Query(value = "UPDATE NotificationEntity n " +
            "SET n.isRead = true " +
            "WHERE n.id = :notificationId")
    void readByNotificationId(Long notificationId);
}
