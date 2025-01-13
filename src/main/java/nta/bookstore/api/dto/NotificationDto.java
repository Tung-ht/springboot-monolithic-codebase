package nta.bookstore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ENotifications;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private ENotifications type;
    private Long userId;
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
