# my.interview_task_Assessment_SpringBoot_JS_Mongo
This project is designed as an interview task to demonstrate advanced development and deployment skills using technologies: Spring Boot, JavaScript, K8s, GCP

# Evaluation Web Application

## Description
This web application is designed for evaluating test results. It accepts score sheets for test participants and stores them, and provides evaluated scores with filtering capabilities.

## Technologies
- **Framework**: Spring Boot (Java 11)
- **Database**: MongoDB
- **Build Tool**: Gradle

## Getting Started

### Prerequisites
Ensure you have the following installed on your system:
- JDK 11
- MongoDB
- Gradle

### Installation and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/PavloSmokorovskiy/my.interview_task_BitcoinCharts_WebFlux_K8s_GCP.git
   git clone git@github.com:PavloSmokorovskiy/my.interview_task_BitcoinCharts_WebFlux_K8s_GCP.git
   cd my.interview_task_BitcoinCharts_WebFlux_K8s_GCP

2. Start MongoDB if installed locally:
   ```bash
   mongod

3. Build and run the application using Gradle:
   ```bash
   gradle bootRun

4. Usage

   ### Adding Test Results
   To submit test result data, use the following request in POSTMAN:
   ```http
   POST /evaluation/sheets
   Content-Type: application/json

   {
      "testeeId": "342",
      "subjects": [
         {
            "subject": "maths",
            "totalQuestions": 100,
            "correct": 72,
            "incorrect": 15
         }
      ]
   }



5. 
