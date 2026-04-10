# Enterprise Data Access Platform

An enterprise-grade back-end service demonstrating unified data access through resilient APIs.
Built using **Java 17**, **Kotlin**, **Spring Boot 3**, **PostgreSQL**, and **AWS SDK**.

## Features
- **Kotlin & Java Interoperability:** Uses Kotlin for concise controller & service logic, while using Java for JPA entities and repositories (demonstrating polyglot capabilities).
- **Unified Data Access:** REST endpoints abstract away the complexity of downstream storage solutions.
- **Relational Storage:** Stores metadata via Spring Data JPA connected to PostgreSQL.
- **Object Storage Simulation:** Contains a simulated AWS S3 service (using `software.amazon.awssdk:s3`), proving readiness for external service integrations.
- **Observability:** Includes Spring Boot Actuator for health and metrics endpoints for deployment readiness.

## Prerequisites
- Java 17+
- Gradle (Optional: Gradle wrapper omitted to keep project clean, if you have Gradle installed `gradle bootRun` will work. If not, see alternatives below.)
- Docker & Docker Compose (for running PostgreSQL locally)

## Running the Application

### 1. Start PostgreSQL Database
```bash
docker-compose up -d
```
This spins up a PostgreSQL alpine container, creating a database called `enterprise_data` with default credentials (`postgres:password`). Database tables will be automatically created on application start via Hibernate (`ddl-auto: update`).

### 2. Build and Run the Application
Run the project using Gradle:
```bash
gradle bootRun
```
*(If you do not have gradle installed locally on your Mac, you can install it via Homebrew: `brew install gradle`)*

### 3. Verify the Endpoints
The platform runs on `localhost:8080`.

**Create an Asset (Simulated Upload + DB Entry)**
```bash
curl -X POST http://localhost:8080/api/v1/assets \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Customer Insights Report Q3",
    "description": "Quarterly aggregation dataset for user metrics.",
    "payload": "{\"some\": \"large base64 or raw data\"}"
  }'
```

**Fetch All Assets**
```bash
curl -H "Accept: application/json" http://localhost:8080/api/v1/assets
```

**Health & Observability Check**
```bash
curl http://localhost:8080/actuator/health
```

## Architecture Details
- **DataController**: Resilient REST APIs handling incoming requests.
- **DataAssetService**: Business component linking our PostgreSQL persistence logic and AWS S3 object layer.
- **AwsStorageService**: Cloud-integration component. Currently simulates network latencies and AWS URLs but can easily be swapped with `S3Client`.
- **DataAsset (Entity)**: Java representation of the PostgreSQL schema.
