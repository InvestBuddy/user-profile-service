package tech.investbuddy.userprofileservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.investbuddy.userprofileservice.model.UserProfile;
import tech.investbuddy.userprofileservice.utile.GenderDeserializer;

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

    @NotBlank(message = "City must not be blank.")
    private String city; // Matches "City" in JSON

    @Min(value = 0, message = "Age must be at least 0.")
    private int age; // Matches "Age" in JSON

    @JsonDeserialize(using = GenderDeserializer.class)
    private UserProfile.Gender gender; // Matches "Gender" in JSON

    @Enumerated(EnumType.STRING)
    private UserProfile.RiskTolerance riskTolerance; // Matches "Risk_Tolernce" in JSON

    @ElementCollection
    private List<String> investmentHistory; // Matches "Investment_History" in JSON

    @Enumerated(EnumType.STRING)
    private UserProfile.FinancialObjective financialObjective;

    @Enumerated(EnumType.STRING)
    private UserProfile.PreferredSector preferredSector; // Matches "Preferred_Sector" in JSON

    @Enumerated(EnumType.STRING)
    private UserProfile.InvestmentFrequency investmentFrequency; // Matches "Invesstment_Frequency" in JSON

    @ElementCollection(targetClass = UserProfile.PreferredDomain.class)
    @Enumerated(EnumType.STRING)
    private List<UserProfile.PreferredDomain> preferredDomains; // Matches "PreferredDomain" in JSON
}
