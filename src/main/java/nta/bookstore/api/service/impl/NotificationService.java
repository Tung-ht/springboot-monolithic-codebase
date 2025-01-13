package nta.bookstore.api.service.impl;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.NotificationDto;
import nta.bookstore.api.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<NotificationDto> getNotificationsByUserId(Long userId) {
        return notificationRepository.findAllByUser_IdOrderByCreatedAtDesc(userId)
                .stream()
                .map(entity -> NotificationDto.builder()
                        .id(entity.getId())
                        .type(entity.getType())
                        .userId(entity.getUser().getId())
                        .message(entity.getMessage())
                        .isRead(entity.getIsRead())
                        .createdAt(entity.getCreatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    public Long countUnreadNotifications(Long userId) {
        return notificationRepository.countByUser_IdAndIsRead(userId, false);
    }

    @Transactional
    public void readNotification(Long notificationId) {
        notificationRepository.readByNotificationId(notificationId);
    }

    @Transactional
    public void readAllNotificationsByUserId(Long userId) {
        notificationRepository.readAllByToUserId(userId);
    }
}
