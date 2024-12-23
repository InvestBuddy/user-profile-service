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

        if(Boolean.TRUE.equals(result)) {
            UserProfile userProfile = UserProfile.builder()
                        .userId(userProfileRequest.getUserId())
                                .expenses(userProfileRequest.getExpenses())
                                        .income(userProfileRequest.getIncome())
                                                .employmentStatus(userProfileRequest.getEmploymentStatus())
                                                        .experienceLevel(userProfileRequest.getExperienceLevel())
                                                                .followsMarketNews(userProfileRequest.isFollowsMarketNews())
                                                                        .investmentBudget(userProfileRequest.getInvestmentBudget())
                                                                                .investmentGoal(userProfileRequest.getInvestmentGoal())
                                                                                        .investmentStyle(userProfileRequest.getInvestmentStyle())
                                                                                                .location(userProfileRequest.getLocation())
                                                                                                        .lossTolerancePercentage(userProfileRequest.getLossTolerancePercentage())
                                                                                                                .maritalStatus(userProfileRequest.getMaritalStatus())
                                                                                                                        .prefersEthicalInvestments(userProfileRequest.isPrefersEthicalInvestments())
                                                                                                                                .riskTolerance(userProfileRequest.getRiskTolerance())
                                                                                                                                        .numberOfDependents(userProfileRequest.getNumberOfDependents())
                                                                                                                                                .preferredInvestDomains(userProfileRequest.getPreferredInvestDomains())
                                                                                                                                                        .prefersPassiveIncome(userProfileRequest.isPrefersPassiveIncome())
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
                .expenses(userProfile.getExpenses())
                .income(userProfile.getIncome())
                .employmentStatus(userProfile.getEmploymentStatus())
                .experienceLevel(userProfile.getExperienceLevel())
                .followsMarketNews(userProfile.isFollowsMarketNews())
                .investmentBudget(userProfile.getInvestmentBudget())
                .investmentGoal(userProfile.getInvestmentGoal())
                .investmentStyle(userProfile.getInvestmentStyle())
                .location(userProfile.getLocation())
                .lossTolerancePercentage(userProfile.getLossTolerancePercentage())
                .maritalStatus(userProfile.getMaritalStatus())
                .prefersEthicalInvestments(userProfile.isPrefersEthicalInvestments())
                .riskTolerance(userProfile.getRiskTolerance())
                .numberOfDependents(userProfile.getNumberOfDependents())
                .preferredInvestDomains(userProfile.getPreferredInvestDomains())
                .prefersPassiveIncome(userProfile.isPrefersPassiveIncome())
                .build();
        return ResponseEntity.ok(userProfileResponse);
    }
}
