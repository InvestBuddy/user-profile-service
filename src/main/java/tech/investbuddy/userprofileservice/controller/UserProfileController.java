package tech.investbuddy.userprofileservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.investbuddy.userprofileservice.dto.UserProfileRequest;
import tech.investbuddy.userprofileservice.dto.UserProfileResponse;
import tech.investbuddy.userprofileservice.service.UserProfileService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserProfile(
            @Valid @RequestBody UserProfileRequest userProfileRequest) {
        userProfileService.save(userProfileRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfileById(@PathVariable UUID id) {
        return userProfileService.getUserProfile(id);
    }
}
