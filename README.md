# User Profile Service - InvestBuddy AI

The **User Profile Service** manages financial profiles for users in the **InvestBuddy AI** platform. It interacts with the **User Service** to validate user existence and ensures accurate profile creation.

---

## 📜 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
---

## ✨ Features

- **Financial Profile Management**:
    - Create, update, and retrieve user profiles.
    - Stores financial data like income, expenses, and risk tolerance.
- **User Existence Validation**:
    - Uses **WebClient** to communicate with the **User Service** before creating a profile.
- **Integration with Discovery Server**:
    - Registers itself for service discovery and enables dynamic communication with the **User Service**.

---

## 🏗️ Architecture

The **User Profile Service** operates as a RESTful microservice and communicates with the **User Service** via **WebClient** for user validation. Key technologies include:

- **Spring Boot**: Backend framework for REST API development.
- **WebClient**: For non-blocking HTTP communication with other microservices.
- **PostgreSQL**: Database for storing user profile data.
- **Spring Cloud**: Integration with Eureka for service discovery.

---

## ✅ Prerequisites

Ensure the following are installed before setting up the **User Profile Service**:

- **Java 21** or higher
- **Maven 3.8** or higher
- **PostgreSQL** (or access to a PostgreSQL instance)
- **Discovery Server** (Eureka)
- **User Service** running and accessible

---

## 🛠️ Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/your-repo/user-profile-service.git
   cd user-profile-service
