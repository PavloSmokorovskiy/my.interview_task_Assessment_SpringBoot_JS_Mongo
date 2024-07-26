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
   or
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
   ```

   Or with CURL:
   ```http
   curl -X POST http://localhost:8080/evaluation/sheets \
   -H "Content-Type: application/json" \
   -d '{
   "testeeId": "342",
   "subjects": [
         {
            "subject": "maths",
            "totalQuestions": 100,
            "correct": 72,
            "incorrect": 15
         }
      ]
   }'
   ```

   ### Retrieving Evaluated Scores
   To fetch data with filters, make a request in POSTMAN:
   ```http
   GET /evaluation/scores?testeeIds=342&subjects=maths,science&scoreRange=70-75
   ```

   Or with CURL:
   ```http
   curl -G http://localhost:8080/evaluation/scores \
   --data-urlencode "testeeIds=342" \
   --data-urlencode "subjects=maths,science" \
   --data-urlencode "scoreRange=70-75"
   ```

5. Testing
   To run the tests, execute the following command:
   ```http
   gradle test
   ```
