package tech.investbuddy.userprofileservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tech.investbuddy.userprofileservice.dto.UserProfileRequest;
import tech.investbuddy.userprofileservice.dto.UserProfileResponse;
import tech.investbuddy.userprofileservice.model.UserProfile;
import tech.investbuddy.userprofileservice.repository.UserProfileRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final WebClient.Builder webClientBuilder;

    public void save(UserProfileRequest userProfileRequest) {
        // Check if the user exists in the User Service
        Boolean result = webClientBuilder.build().get()
                .uri("http://user-service/api/v1/users/" + userProfileRequest.getUserId() + "/exists")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            // Map the request to the entity
            UserProfile userProfile = UserProfile.builder()
                    .userId(userProfileRequest.getUserId())
                    .gender(userProfileRequest.getGender())
                    .city(userProfileRequest.getCity())
                    .age(userProfileRequest.getAge())
                    .income(userProfileRequest.getIncome())
                    .riskTolerance(userProfileRequest.getRiskTolerance())
                    .investmentFrequency(userProfileRequest.getInvestmentFrequency())
                    .preferredSector(userProfileRequest.getPreferredSector())
                    .preferredDomains(userProfileRequest.getPreferredDomains())
                    .investmentHistory(userProfileRequest.getInvestmentHistory())
                    .financialObjective(userProfileRequest.getFinancialObjective()) // Ensure this is set

                    .build();

            userProfileRepository.save(userProfile);
        } else {
            throw new IllegalArgumentException("User does not exist");
        }
    }

    public ResponseEntity<UserProfileResponse> getUserProfile(UUID userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId);
        if (userProfile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .userId(userProfile.getUserId())
                .city(userProfile.getCity())
                .age(userProfile.getAge())
                .gender(userProfile.getGender())
                .income(userProfile.getIncome())
                .riskTolerance(userProfile.getRiskTolerance())
                .investmentFrequency(userProfile.getInvestmentFrequency())
                .preferredSector(userProfile.getPreferredSector())
                .preferredDomains(userProfile.getPreferredDomains()) // Directly set the list
                .investmentHistory(userProfile.getInvestmentHistory()) // Directly set the list
                .financialObjective(userProfile.getFinancialObjective()) // Ensure this is set

                .build();

        return ResponseEntity.ok(userProfileResponse);
    }



    public ResponseEntity<String> deleteUserProfile(UUID userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId);
        if (userProfile != null) {
            userProfileRepository.delete(userProfile);
            return ResponseEntity.ok("Deleted user profile");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
