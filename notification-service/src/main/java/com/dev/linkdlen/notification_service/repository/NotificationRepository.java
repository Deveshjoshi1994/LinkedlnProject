package com.dev.linkdlen.notification_service.repository;

import com.dev.linkdlen.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
