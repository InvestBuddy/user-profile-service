package tech.investbuddy.userprofileservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID userId;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Double income;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskTolerance riskTolerance;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "investment_history", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "domain")
    private List<String> investmentHistory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinancialObjective financialObjective;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferredSector preferredSector;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestmentFrequency investmentFrequency;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "preferred_domains", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "domain")
    private List<String> preferredDomain;

    /**
     * Méthode appelée avant la persistance d'un nouvel enregistrement.
     */
    @PrePersist
    @PreUpdate
    private void calculateAge() {
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    // Enums

    public enum Gender {
        WOMAN, MAN
    }

    public enum RiskTolerance {
        LOW, MEDIUM, HIGH
    }

    public enum FinancialObjective {
        RETRAITE, EPARGNE_DE_SECURITE, ACHAT_IMMOBILIER, EDUCATION_DES_ENFANTS, VOYAGES, EPARGNE_DE_DEPART, INVESTIR_DANS_EDUCATION
    }

    public enum PreferredSector {
        ACTIONS, CRYPTOMONNAIES, ETF, IMMOBILIER, OBLIGATIONS, STARTUPS
    }

    public enum InvestmentFrequency {
        MENSUEL, TRIMESTRIEL, ANNUEL
    }
}