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

## Security Configuration

This template uses Spring Security with the following configuration:

- **HTTP Basic Authentication** is enabled for all endpoints except `GET /api/executions`, which is public.
- **User Details:**
  - Username: `user`
  - Password: `password` (BCrypt encoded)
  - Role: `SYSTEM`
- **Access Rules:**
  - `GET /api/executions`: Public (no authentication required)
  - All other endpoints: Require authentication and the `SYSTEM` role
- **Configuration Location:** See `src/main/java/com/quetoquenana/template/config/SecurityConfig.java` for details.

## Example Feature: Execution Tracking Table

This template includes a complete example of tracking application executions:

- **Database Table:** Automatically created using Flyway migrations (`executions` table).
- **Model:** Java entity for executions, using @JsonView for API responses.
- **Repository:** Spring Data JPA repository for CRUD operations.
- **Service:** Business logic for saving and retrieving executions, including paginated queries.
- **Controller:** REST API to view executions (`/api/executions`), with endpoints for list, detail, and paginated results. The app uses the artifactId as context path, so the full path is `/template/api/executions`.
- **Startup Logic:** Records a new execution each time the app starts.
- **Unit Tests:** Comprehensive test cases for the controller and service, including JsonView and pagination.
- **Postman Collection:** Example requests to test the API, stored in the `.postman` folder.

## Getting Started

1. **Clone this repository** and update the project information as needed.
2. **Configure your database** in the properties files (`application-dev.properties`, `application-prd.properties`).
3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API:**

   - List executions: `GET /api/executions` (public)
   - Get execution by ID: `GET /api/executions/{id}` (requires authentication)
   - Paginated executions: `GET /api/executions/page?page=0&size=10` (requires authentication)
   - Monitor API: `GET /actuator/health` (requires authentication)

5. **Test with Postman:**
   - Import the collection from the `.postman` folder and run example requests.

## Customization

- Update the `pom.xml` to add or remove dependencies as needed.
- Modify the package structure and application properties to fit your requirements.
- Extend the execution tracking feature or add new features as needed.

## License

This template is provided as-is for bootstrapping new Spring Boot projects.
