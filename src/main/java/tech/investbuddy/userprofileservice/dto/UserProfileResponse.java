package tech.investbuddy.userprofileservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String city; // Matches "City" in JSON
    private int age; // Matches "Age" in JSON
    private UserProfile.Gender gender; // Matches "Gender" in JSON
    private float income;
    private UserProfile.RiskTolerance riskTolerance; // Matches "Risk_Tolernce" in JSON
    private UserProfile.InvestmentFrequency investmentFrequency; // Matches "Investment_Frequency" in JSON
    private UserProfile.FinancialObjective financialObjective; // Matches "Financial_Objective" in JSON
    private UserProfile.PreferredSector preferredSector; // Matches "Preferred_Sector" in JSON
    private List<UserProfile.PreferredDomain> preferredDomains; // Matches "PreferredDomain" in JSON
    private List<String> investmentHistory; // Matches "Investment_History" in JSON
}

