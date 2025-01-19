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

    private String city; // Matches "City" in JSON

    private int age; // Matches "Age" in JSON

    @Enumerated(EnumType.STRING)
    private Gender gender; // Matches "Gender" in JSON: 'woman', 'man'

    @Enumerated(EnumType.STRING)
    private RiskTolerance riskTolerance; // Matches "Risk_Tolerance" in JSON: 'low', 'medium', 'high'

    @ElementCollection
    @CollectionTable(name = "investment_history", joinColumns = @JoinColumn(name = "user_profile_id"))
    private List<String> investmentHistory; // Matches "Investment_History" in JSON

    @Enumerated(EnumType.STRING)
    @Column(name = "financial_objective", nullable = false)
    private FinancialObjective financialObjective; // Matches "Financial_Objective" in JSON

    @Enumerated(EnumType.STRING)
    @Column(name = "Preferred_Sector", nullable = false)
    private PreferredSector preferredSector; // Matches "Preferred_Sector" in JSON

    @Enumerated(EnumType.STRING)
    private InvestmentFrequency investmentFrequency; // Matches "Investment_Frequency" in JSON

    @ElementCollection(targetClass = PreferredDomain.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_preferred_invest_domains", joinColumns = @JoinColumn(name = "user_profile_id"))
    private List<PreferredDomain> preferredDomains; // Matches "PreferredDomain" in JSON

    // Enums definitions

    public enum Gender {
        MAN("man"),
        WOMAN("woman");

        private final String value;

        Gender(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Gender fromValue(String value) {
            for (Gender gender : Gender.values()) {
                if (gender.value.equalsIgnoreCase(value)) {
                    return gender;
                }
            }
            throw new IllegalArgumentException("Invalid Gender value: " + value);
        }
    }

    public enum RiskTolerance {
        LOW("low"),
        MEDIUM("medium"),
        HIGH("high");

        private final String value;

        RiskTolerance(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FinancialObjective {
        RETIREMENT("retraite"),
        SAVINGS("épargne de sécurité"),
        REAL_ESTATE_PURCHASE("achat immobilier"),
        CHILDREN_EDUCATION("éducation des enfants"),
        TRAVEL("voyages"),
        STARTER_SAVINGS("épargne de départ"),
        EDUCATION_INVESTMENT("investir dans l'éducation");

        private final String value;

        FinancialObjective(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static FinancialObjective fromValue(String value) {
            for (FinancialObjective objective : FinancialObjective.values()) {
                if (objective.value.equalsIgnoreCase(value)) {
                    return objective;
                }
            }
            throw new IllegalArgumentException("Invalid Financial Objective value: " + value);
        }
    }

    public enum PreferredSector {
        ACTIONS("actions"),
        CRYPTOCURRENCIES("cryptomonnaies"),
        ETF("ETF"),
        IMMOBILIER("immobilier"),
        OBLIGATIONS("obligations"),
        STARTUPS("startups");

        private final String value;

        PreferredSector(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static PreferredSector fromValue(String value) {
            for (PreferredSector sector : PreferredSector.values()) {
                if (sector.value.equalsIgnoreCase(value)) {
                    return sector;
                }
            }
            throw new IllegalArgumentException("Invalid Preferred Sector value: " + value);
        }
    }

    public enum InvestmentFrequency {
        MENSUEL("mensuel"),
        TRIMESTRIEL("trimestriel"),
        ANNUEL("annuel");

        private final String value;

        InvestmentFrequency(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static InvestmentFrequency fromValue(String value) {
            for (InvestmentFrequency frequency : InvestmentFrequency.values()) {
                if (frequency.value.equalsIgnoreCase(value)) {
                    return frequency;
                }
            }
            throw new IllegalArgumentException("Invalid Investment Frequency value: " + value);
        }
    }

    public enum PreferredDomain {
        ACTIONS("Actions"),
        CRYPTOCURRENCIES("Cryptomonnaies"),
        REAL_ESTATE("Immobilier"),
        BONDS("Obligations"),
        STARTUPS("Startups"),
        COMMODITIES("Matières premières"),
        ETF("ETF"),
        FUNDRAISING("Fundraising"),
        SOCIAL_INVESTMENT("Investissement Socialement Responsable");

        private final String value;

        PreferredDomain(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
