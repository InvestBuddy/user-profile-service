package tech.investbuddy.userprofileservice.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.investbuddy.userprofileservice.model.UserProfile;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {

    @NotNull(message = "User ID is required.")
    private UUID userId;

    @PositiveOrZero(message = "Income must be zero or a positive number.")
    private float income;

    @NotBlank(message = "Location must not be blank.")
    private String location;

    @Enumerated(EnumType.STRING)
    private UserProfile.RiskTolerance riskTolerance;

    @Enumerated(EnumType.STRING)
    private UserProfile.InvestmentGoal investmentGoal;

    @PositiveOrZero(message = "Expenses must be zero or a positive number.")
    private float expenses;

    @PositiveOrZero(message = "Investment budget must be zero or a positive number.")
    private float investmentBudget;

    
    private boolean prefersEthicalInvestments;

    @Enumerated(EnumType.STRING)
    private UserProfile.MaritalStatus maritalStatus;

    
    private boolean prefersPassiveIncome;

    @Enumerated(EnumType.STRING)
    private UserProfile.ExperienceLevel experienceLevel;

    private List<UserProfile.PreferredDomain> preferredInvestDomains;

    private boolean followsMarketNews;

    private int numberOfDependents;

    @Min(value = 0, message = "Loss tolerance percentage must be at least 0%.")
    @Max(value = 100, message = "Loss tolerance percentage must not exceed 100%.")
    private float lossTolerancePercentage;

    @Enumerated(EnumType.STRING)
    private UserProfile.EmploymentStatus employmentStatus;

    @Enumerated(EnumType.STRING)
    private UserProfile.InvestmentStyle investmentStyle;
}
