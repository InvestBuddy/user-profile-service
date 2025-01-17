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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final WebClient.Builder webClientBuilder;

    public void save(UserProfileRequest userProfileRequest) {

        // call User Service to know if the user really exist, before creating a new profile

        Boolean result = webClientBuilder.build().get()
                .uri("http://user-service/api/v1/users/"+userProfileRequest.getUserId()+"/exists")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            UserProfile userProfile = UserProfile.builder()
                    .userId(userProfileRequest.getUserId())
                    .city(userProfileRequest.getCity())
                    .dateOfBirth(userProfileRequest.getDateOfBirth())
                    .income(userProfileRequest.getIncome())
                    .gender(userProfileRequest.getGender())
                    .riskTolerance(userProfileRequest.getRiskTolerance())
                    .investmentHistory(userProfileRequest.getInvestmentHistory())
                    .financialObjective(userProfileRequest.getFinancialObjective())
                    .preferredSector(userProfileRequest.getPreferredSector())
                    .investmentFrequency(userProfileRequest.getInvestmentFrequency())
                    .preferredDomain(userProfileRequest.getPreferredDomain())
                    .build();
            userProfileRepository.save(userProfile);
        }else {
            throw new IllegalArgumentException("User does not exist");
        }
    }

    public ResponseEntity<UserProfileResponse> getUserProfile(UUID userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId);
        if (userProfile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        UserProfileResponse userProfileResponse = UserProfileResponse.builder()
                .userId(userProfile.getUserId())
                .city(userProfile.getCity())
                .dateOfBirth(userProfile.getDateOfBirth())
                .age(userProfile.getAge())  // L'âge est calculé dans la classe UserProfile
                .income(userProfile.getIncome())
                .gender(userProfile.getGender())
                .riskTolerance(userProfile.getRiskTolerance())
                .investmentHistory(userProfile.getInvestmentHistory())
                .financialObjective(userProfile.getFinancialObjective())
                .preferredSector(userProfile.getPreferredSector())
                .investmentFrequency(userProfile.getInvestmentFrequency())
                .preferredDomain(userProfile.getPreferredDomain())
                .build();
        return ResponseEntity.ok(userProfileResponse);
    }

    public ResponseEntity<String> deleteUserProfile(UUID userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId);
        if(userProfile != null) {
            userProfileRepository.delete(userProfile);
            return ResponseEntity.ok("Deleted user profile");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
