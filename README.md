1. Project Title and Description 
Title: Retail Rewards Calculation Application
Description: This project is a Spring Boot-based RESTful API designed to calculate reward points for retail customers.  Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.  
The rewards are calculated using the following logic:  
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction. 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points). 


2. Prerequisites
Before running this application, ensure you have the following installed:
Java Development Kit (JDK) 17 or higher.  
Apache Maven 3.8+.  
An IDE (IntelliJ IDEA, Eclipse, or VS Code).  
A web browser or API client like Postman for testing endpoints. 


3. Installation and Configuration
Follow these steps to set up the project locally:

Clone the Repository: git clone <your-repository-url>

cd 

Configuration:

The application uses an in-memory H2 Database by default, so no external database installation is required. 
Configuration settings can be found in:src/main/resources/application.properties.  
Initial Data: Pre-defined customer and transaction records are automatically loaded from src/main/resources/data.sql upon startup.  


4. Running the application
You can run the application using Maven directly from your terminal:


Build the project and install dependencies
mvn clean install

Start the Spring Boot application
mvn spring-boot:run

Once started, the application will be available at http://localhost:8080.

5. API Endpoints Documentation

The application exposes the following RESTful endpoints under the base path /api/v1. 
All request and response bodies are in JSON format.

Rewards Endpoints

Method       Endpoint           				Description
GET			/rewards/customers/{customerId}		Retrieves monthly and total reward points for a specific customer.
GET			/rewards							Retrieves a summary of reward points for all registered customers.

Transaction Endpoints

Method		Endpoint							Description
POST		/transactions						Records a new purchase. Requires a JSON body containing customerId, amount, and transactionDate.
GET			/transactions						Retrieves a complete history of all recorded transactions in the system.

Customer Endpoints

Method		Endpoint							Description
POST		/customers							Registers a new customer. Requires a JSON body with customerName and customerEmail.
GET			/customers							Retrieves a list of all registered customers and their profile details.

Quick Integration Guide

Base URL: http://localhost:8080/api/v1
Content-Type: application/json
Validation: The POST /transactions endpoint uses @Valid to ensure data integrity (e.g., non-negative amounts or required fields).
Response Codes:
200 OK: Successful retrieval of data.
201 Created: Successful creation of a Customer or Transaction.
400 Bad Request: Input validation failed.
404 Not Found: The requested Customer ID does not exist.


6. Testing Instructions
The project includes unit and integration tests to ensure calculation accuracy and API reliability. 
To run all tests:
mvn test

Unit & Integration Tests : Located in src/test/java/.../service. 