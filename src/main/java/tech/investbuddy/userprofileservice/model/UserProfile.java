package tech.investbuddy.userprofileservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserProfile {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private UUID userId;

    private float income;

    private String location;

    private String gender;

    @Enumerated(EnumType.STRING)
    private RiskTolerance riskTolerance;

    @Enumerated(EnumType.STRING)
    private InvestmentGoal investmentGoal;

    private float expenses;

    private float investmentBudget;

    private boolean prefersEthicalInvestments;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private boolean prefersPassiveIncome;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @ElementCollection(targetClass = PreferredDomain.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_preferred_invest_domains", joinColumns = @JoinColumn(name = "user_profile_id"))
    private List<PreferredDomain> preferredInvestDomains;

    private boolean followsMarketNews;

    private int numberOfDependents;

    private float lossTolerancePercentage;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @Enumerated(EnumType.STRING)
    private InvestmentStyle investmentStyle;

    public enum RiskTolerance {
        LOW, MEDIUM, HIGH
    }

    public enum InvestmentGoal {
        RETIREMENT, GROWTH, SAVINGS, OTHER
    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED
    }

    public enum ExperienceLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

/*    public enum PreferredDomain {
//        STOCKS,
//        ETF,
//        REAL_ESTATE,
//        BONDS,
//        SOCIALLY_RESPONSIBLE_INVESTMENT,
//        CRYPTOCURRENCIES,
//        STARTUPS,
//        COMMODITIES
    }*/

    public enum PreferredDomain {
        actions,
        cryptomonnaies,
        ETF,
        immobilier,
        obligations,
        startups
    }


    public enum InvestmentStyle {
        LONG_TERM, SHORT_TERM, ACTIVE_TRADING
    }

    public enum EmploymentStatus {
        EMPLOYED, SELF_EMPLOYED, RETIRED, STUDENT, UNEMPLOYED
    }

}
