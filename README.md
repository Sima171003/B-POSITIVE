# 🩸 B-POSITIVE — Blood Donation & Volunteer Management Platform

B-POSITIVE is a full-stack web application designed to simplify the process of connecting blood donors, volunteers, and people in need of blood support through a centralized digital platform.

The project is currently under active development and is being built as a collaborative full-stack application, with a dedicated frontend and backend structure.

---

## 🚀 Project Status

> **Current Status:** In Progress / Under Development

B-POSITIVE is not yet a fully completed production-level application. The core project structure, frontend pages, backend architecture, and database integration setup are being developed step by step.

This repository is maintained as a full-stack project to demonstrate the development process, backend implementation, frontend integration, and future scalability of the platform.

---

## 📌 Project Objective

The main goal of B-POSITIVE is to create a platform where:

- Users can access blood donation-related services.
- Donors can register and interact with the platform.
- Volunteers can participate in blood donation support activities.
- The system can manage user, donor, and volunteer-related data through backend APIs.
- Future versions can include admin control, authentication, request management, and notification features.

---

## 👩‍💻 My Contribution

I worked on the **backend development** of this project.

My backend contribution includes:

- Designing the Spring Boot backend structure
- Creating backend packages using layered architecture
- Working with entity, controller, service, repository, DTO, config, and exception layers
- Setting up database connectivity with MySQL
- Adding required backend dependencies
- Preparing the backend for REST API development
- Structuring the project for future frontend-backend integration

The frontend part of the project was developed by my husband as part of our collaborative full-stack work.

---

## 🤝 Collaboration

| Contributor | Contribution |
|---|---|
| **Sima Chakraborty** | Backend Development |
| **Pratik Naskar** | Frontend Development |

> This project is a collaborative full-stack application where the frontend and backend are developed separately but maintained in the same repository for better integration and project tracking.

---

## 🛠️ Tech Stack

### Frontend

- HTML5
- CSS3
- JavaScript

### Backend

- Java
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Maven
- Lombok
- MySQL Connector
- Spring Mail
- Swagger / OpenAPI

### Database

- MySQL

---

## 📁 Repository Structure

```bash
B-POSITIVE/
│
├── b_plus_frontend/
│   ├── resources/
│   ├── script/
│   ├── styles/
│   ├── donor.html
│   ├── home.html
│   ├── index.html
│   ├── login-signup.html
│   ├── user-donorPortal.html
│   ├── volunteer.html
│   └── volunteerPortal.html
│
├── backend/
│   ├── .mvn/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/bplus/backend/
│   │       │       ├── Entity/
│   │       │       ├── config/
│   │       │       ├── controller/
│   │       │       ├── dto/
│   │       │       ├── exception/
│   │       │       ├── repo/
│   │       │       ├── service/
│   │       │       └── BplusBackendApplication.java
│   │       │
│   │       └── resources/
│   │           └── application.properties
│   │
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
│
├── database/
│
└── README.md
