package com.dev.linkdlen.notification_service.service;

import com.dev.linkdlen.notification_service.entity.Notification;
import com.dev.linkdlen.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification addNotification(Notification notification) {
        log.info("Adding Notification to DB, message: {}", notification.getMessage());
        notification = notificationRepository.save(notification);


        //SendMailer to sent email  can be done

        //for Android FCM library  can be done
        return notification;
    }
}
