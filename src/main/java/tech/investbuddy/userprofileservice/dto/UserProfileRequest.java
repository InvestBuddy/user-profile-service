package tech.investbuddy.userprofileservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.investbuddy.userprofileservice.model.UserProfile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {

    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 100, message = "City name cannot exceed 100 characters")
    private String city;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Income cannot be null")
    @Positive(message = "Income must be a positive value")
    private Double income;

    @NotNull(message = "Gender cannot be null")
    private UserProfile.Gender gender;

    @NotNull(message = "Risk tolerance cannot be null")
    private UserProfile.RiskTolerance riskTolerance;

    @Size(max = 10, message = "Investment history cannot have more than 10 items")
    private List<String> investmentHistory;

    @NotNull(message = "Financial objective cannot be null")
    private UserProfile.FinancialObjective financialObjective;

    @NotNull(message = "Preferred sector cannot be null")
    private UserProfile.PreferredSector preferredSector;

    @NotNull(message = "Investment frequency cannot be null")
    private UserProfile.InvestmentFrequency investmentFrequency;

    @Size(max = 5, message = "Preferred domains cannot have more than 5 items")
    private List<String> preferredDomain;
}
