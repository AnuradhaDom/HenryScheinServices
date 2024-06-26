package com.henryschein.notificationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henryschein.notificationService.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
