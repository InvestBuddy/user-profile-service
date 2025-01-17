package tech.investbuddy.userprofileservice.dto;

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
public class UserProfileResponse {

    private Long id;

    private UUID userId;

    private String city;

    private LocalDate dateOfBirth;

    private Integer age;

    private Double income;

    private UserProfile.Gender gender;

    private UserProfile.RiskTolerance riskTolerance;

    private List<String> investmentHistory;

    private UserProfile.FinancialObjective financialObjective;

    private UserProfile.PreferredSector preferredSector;

    private UserProfile.InvestmentFrequency investmentFrequency;

    private List<String> preferredDomain;
}
