# Spring Boot Aspect-Oriented Programming (AOP) Lab

This lab demonstrates how Spring AOP enables the separation of cross-cutting concerns from core business logic through reusable aspects, advice, pointcuts, and proxy-based method interception.


## Implemented Features
- **LoggingAspect:** Demonstrates `@Before`, `@After`, `@AfterReturning`, and `@AfterThrowing`.
- **PerformanceAspect:** Uses `@Around` and `ProceedingJoinPoint` to measure method execution time.
- **AuditAspect:** Uses a custom `@Auditable` annotation to selectively audit operations.
- **ExceptionHandlingAspect:** Monitors exceptions thrown by service methods.
- **Aspect Ordering:** Demonstrates execution precedence using `@Order`.

## Application Structure
The application follows a layered architecture:

- **Controller:** Exposes REST API endpoints.
- **Service:** Contains student, course, and payment business logic.
- **Aspect:** Handles cross-cutting concerns.
- **Annotation:** Defines custom annotations for selective auditing.
- **Model:** Contains application data structures.


## Sample API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/students` | Register a student |
| GET | `/api/students/{id}` | Retrieve a student |
| DELETE | `/api/students/{id}` | Delete a student |
| POST | `/api/courses` | Create a course |
| GET | `/api/courses/{id}` | Retrieve a course |
| POST | `/api/payments` | Process a payment |


