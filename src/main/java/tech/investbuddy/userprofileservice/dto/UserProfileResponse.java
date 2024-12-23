package tech.investbuddy.userprofileservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class UserProfileResponse {
    private UUID userId;
    private float income;
    private String location;
    private UserProfile.RiskTolerance riskTolerance;
    private UserProfile.InvestmentGoal investmentGoal;
    private float expenses;
    private float investmentBudget;
    private boolean prefersEthicalInvestments;
    private UserProfile.MaritalStatus maritalStatus;
    private boolean prefersPassiveIncome;
    private UserProfile.ExperienceLevel experienceLevel;
    private List<UserProfile.PreferredDomain> preferredInvestDomains;
    private boolean followsMarketNews;
    private int numberOfDependents;
    private float lossTolerancePercentage;
    private UserProfile.EmploymentStatus employmentStatus;
    private UserProfile.InvestmentStyle investmentStyle;
}
