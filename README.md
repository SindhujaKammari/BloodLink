<h1 align="center">🩸 BLOOD LINK</h1> <p align="center"> A free, hyperlocal blood donor finder for Hyderabad. </p> <p align="center"> <img src="https://img.shields.io/badge/Java-17-orange" alt="Java 17" /> <img src="https://img.shields.io/badge/Spring%20Boot-Backend-brightgreen" alt="Spring Boot" /> <img src="https://img.shields.io/badge/MySQL-Database-blue" alt="MySQL" /> <img src="https://img.shields.io/badge/Status-In%20Progress-yellow" alt="Status" /> </p>
About

When someone urgently needs blood, finding a donor usually means forwarding messages through dozens of WhatsApp groups and waiting.

BLOOD LINK keeps a registry of willing donors by blood group and area, so a matching donor can be found in minutes.

⚠️ Important: RaktMitra only connects people. Every donation must happen through a hospital or licensed blood bank, where the blood is tested and the donor is screened by medical staff.

Current Status
✅ Donor module: donors can be registered and stored in the database
🔜 Next: search donors by blood group and area
🔜 After that: React frontend
Tech Stack
Backend: Java, Spring Boot, Spring Web
Database: MySQL with Spring Data JPA (Hibernate)
Utilities: Lombok
API testing: Postman
Frontend: React (planned)
How It Works
Client (Postman / React)  →  Spring Boot API  →  MySQL

All reads and writes go through the API. Nothing talks to the database directly.

Data Model

The donor entity contains an auto-generated `donorId`, required `donorName`, `bloodType`, `phoneNum`, `area`, `mandal`, and `district`, plus optional `donorEmail` and `lastDonate` strings.
API

Base URL: http://localhost:8080

Register a donor

POST /donor/register

Request body:

json
{
  "donorName": "Test Donor",
  "bloodType": "O+",
  "area": "Kukatpally",
  "mandal": "Kukatpally",
  "district": "Hyderabad",
  "phoneNum": "9999999999",
  "lastDonate": "2026-08-31"
}

Returns HTTP 202 when the donor is saved.

Getting Started

Prerequisites: JDK 17+, Maven, MySQL 8+

1. Clone the repo
bash
git clone <your-repo-url>
cd BloodLink
2. Create the database
sql
CREATE DATABASE bloodlink;
3. Configure the database

Set database credentials in PowerShell before starting the app:

```powershell
$env:DB_USERNAME = "YOUR_USERNAME"
$env:DB_PASSWORD = "YOUR_PASSWORD"
```

The app connects to `jdbc:mysql://localhost:3306/bloodlink`; create that database first.
4. Run the app
bash
mvn spring-boot:run

The API starts on http://localhost:8080 and the donor table is created automatically.

5. Try it

Send a POST /donor/register request from Postman and check that the row appears in MySQL.

Roadmap
 Search donors by blood group and area
 Hide donors who gave blood in the last 3 months
 Return only name and area in search results, never the phone number
 React frontend for registration and search
 Blood request posting with urgency and hospital name
 SMS or WhatsApp alerts to matching donors
 Phone OTP verification to prevent fake signups
Privacy and Safety
Only the minimum data needed to match donors is collected.
No medical history or health reports are stored.
Eligibility is self-declared. Final screening and blood testing are done by the hospital or blood bank at the time of donation.


Author

Built by Sindhu.
