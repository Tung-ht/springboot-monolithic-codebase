package nta.bookstore.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.NotificationDto;
import nta.bookstore.api.service.impl.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public AppResponse<List<NotificationDto>> getNotificationsByUserId(@RequestParam(name = "userId") Long userId) {
        return AppResponse.ok(notificationService.getNotificationsByUserId(userId));
    }

    @Operation(summary = "call this api per 5s.")
    @GetMapping("/count-unread")
    public AppResponse<Long> countUnreadNotifications(@RequestParam(name = "userId") Long userId) {
        return AppResponse.ok(notificationService.countUnreadNotifications(userId));
    }

    @PutMapping("read/{id}")
    public AppResponse<?> readNotification(@PathVariable(name = "id") Long notificationId) {
        notificationService.readNotification(notificationId);
        return AppResponse.ok();
    }

    @PutMapping("read-all")
    public AppResponse<?> readAllNotificationsByUserId(@RequestParam(name = "userId") Long userId) {
        notificationService.readAllNotificationsByUserId(userId);
        return AppResponse.ok();
    }
}
