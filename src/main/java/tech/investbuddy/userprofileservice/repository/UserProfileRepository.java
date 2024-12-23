package tech.investbuddy.userprofileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.investbuddy.userprofileservice.model.UserProfile;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUserId(UUID userId);
}
