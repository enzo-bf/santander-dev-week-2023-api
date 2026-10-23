# Task Manager API

REST API for task management built with Java 17, Spring Boot 3, Spring Data JPA, Bean Validation, and OpenAPI.

## Architecture

The application follows a layered structure:

- `controller`: REST endpoints, request validation, and HTTP response mapping.
- `controller.dto`: API request and response contracts, independent of JPA entities.
- `service`: task use cases and business rules.
- `domain.model`: JPA `Task` entity and task enums.
- `domain.repository`: Spring Data JPA persistence access.
- `controller.exception`: centralized HTTP error mapping.

Task status and priority are stored as enum names. Creation and update timestamps are maintained by JPA lifecycle callbacks. New tasks default to `PENDING` status and `MEDIUM` priority when those values are omitted.

## Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/tasks` | List all tasks; accepts optional `status` and `priority` filters |
| `GET` | `/tasks/{id}` | Retrieve a task |
| `POST` | `/tasks` | Create a task |
| `PUT` | `/tasks/{id}` | Update task title and description; status and priority are optional |
| `DELETE` | `/tasks/{id}` | Delete a task |
| `PATCH` | `/tasks/{id}/complete` | Mark a task as completed |

Allowed status values: `PENDING`, `IN_PROGRESS`, `COMPLETED`. Allowed priority values: `LOW`, `MEDIUM`, `HIGH`.

Request validation requires a non-blank title of at most 100 characters. Description is limited to 500 characters. Invalid request values return `400`; missing tasks return `404`.

### Examples

Create a task:

```http
POST /tasks
Content-Type: application/json

{
  "title": "Prepare project proposal",
  "description": "Draft the scope and delivery estimate",
  "priority": "HIGH"
}
```

Filter by status and/or priority:

```http
GET /tasks?status=PENDING
GET /tasks?priority=HIGH
GET /tasks?status=IN_PROGRESS&priority=HIGH
```

Update a task:

```http
PUT /tasks/1
Content-Type: application/json

{
  "title": "Prepare project proposal",
  "description": "Include scope, estimate, and timeline",
  "status": "IN_PROGRESS",
  "priority": "HIGH"
}
```

Complete a task:

```http
PATCH /tasks/1/complete
```

## Run locally

Use JDK 17 and run:

```bash
./gradlew bootRun
```

On Windows:

```powershell
.\gradlew.bat bootRun
```

The `dev` profile is active by default and uses an in-memory H2 database. Run the test suite with `./gradlew test` (or `.\gradlew.bat test` on Windows).

## Swagger / OpenAPI

When the application is running, Swagger UI is available at `/swagger-ui.html` and the OpenAPI document at `/v3/api-docs`.

## Railway deployment

Deploy this repository as a Railway service using the Gradle build. Provision a PostgreSQL database and set these service variables from the database connection details:

- `SPRING_PROFILES_ACTIVE=prd`
- `PGHOST`
- `PGPORT`
- `PGDATABASE`
- `PGUSER`
- `PGPASSWORD`

Railway's `PORT` is used by the production profile. Flyway applies the versioned database migration at startup, then Hibernate validates the schema. Do not use Hibernate schema auto-update as a production migration strategy.
