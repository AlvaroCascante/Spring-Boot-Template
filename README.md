# Template Spring Boot Project

This repository is a template for creating new Spring Boot projects with a pre-configured set of essential dependencies and features. It is designed to help you quickly bootstrap robust, production-ready applications.

## Project Information

- **Group:** `com.quetoquenana`
- **Artifact:** `template`
- **Name:** `Template`
- **Description:** Template to create new Spring Boot projects
- **Package name:** `com.quetoquenana.template`

## Included Dependencies

This template includes the following dependencies by default:

- **Spring Boot DevTools**: For rapid development and automatic restarts.
- **Lombok**: Simplifies Java code with annotations for boilerplate reduction.
- **Docker Compose Support**: Easily run dependent services (e.g., databases) with Docker Compose.
- **Spring Web**: Build RESTful web services and web applications.
- **Spring Data JPA**: Simplifies data access and ORM with JPA.
- **Spring Boot Actuator**: Provides monitoring and management endpoints.
- **Spring Security**: Adds authentication and authorization capabilities.
- **Flyway Migration**: Manages database schema migrations.
- **PostgreSQL Driver**: Connects to PostgreSQL databases.

## Example Feature: Execution Tracking Table

This template includes a complete example of tracking application executions:

- **Database Table:** Automatically created using Flyway migrations (`executions` table).
- **Model:** Java entity for executions.
- **Repository:** Spring Data JPA repository for CRUD operations.
- **Service:** Business logic for saving and retrieving executions.
- **Controller:** REST API to view executions (`/api/executions`). Note that the app starts using the artifactId name in this case would be `/template`. In other words, full path to access executions would be `/template/api/executions`.
- **Startup Logic:** Records a new execution each time the app starts.
- **Unit Tests:** Comprehensive test cases for the controller and service.
- **Postman Collection:** Example requests to test the API, stored in the `.postman` folder.

## Getting Started

1. **Clone this repository** and update the project information as needed.
2. **Configure your database** in the properties files (`application-dev.properties`, `application-prd.properties`).
3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API:**

   - List executions: `GET /api/executions`
   - Get execution by ID: `GET /api/executions/{id}`
   - Monitor API: `GET /actuator/health` (requires default Spring Boot credentials)

5. **Test with Postman:**
   - Import the collection from the `.postman` folder and run example requests.

## Customization

- Update the `pom.xml` to add or remove dependencies as needed.
- Modify the package structure and application properties to fit your requirements.
- Extend the execution tracking feature or add new features as needed.

## License

This template is provided as-is for bootstrapping new Spring Boot projects.
