

# EPAM Microservices Project

## Overview

The EPAM Microservices Project demonstrates a sophisticated fitness application architecture, leveraging the power of microservices for scalability, flexibility, and resilience. It encompasses a range of services including authentication, configuration management, service discovery, a gateway for routing, the main fitness application logic, messaging, and workload management.

## Prerequisites

Before you begin, ensure you have the following installed:
- **Java JDK 11+**: The core language runtime.
- **Maven 3.6.3+**: For dependency management and project build.
- **Docker**: For creating and managing containers (optional but recommended).

## Getting Started

### 1. Clone the Project

Start with cloning the project repository to your local machine using Git.

### 2. Environment Setup

Verify that Java and Maven are installed by running `java -version` and `mvn -v` in your terminal. Install Docker to enable container-based deployment.

### 3. Configure Services

Navigate to each service's directory to adjust the `application-default.properties` for default settings and `application-docker.properties` for Docker environments.

## Running the Services

### Building and Running with Maven

To run a service locally:

1. Open a terminal and navigate to the service directory.
2. Execute `mvn clean install` to build the service.
3. Start the service with `mvn spring-boot:run`.

### Using Docker for Deployment

For Docker deployment:

1. Ensure Docker is running on your system.
2. Build Docker images for each service with provided Dockerfiles.
3. Use `docker-compose up` or individual `docker run` commands to start the services.

## Architecture and Interactions

- **Config Server**: Manages all configurations centrally.
- **Discovery Server**: Facilitates service discovery and registration.
- **Gateway**: The entry point for routing to various services.
- **Auth Service & Main Fitness**: Implements specific business logic.

Detailed interaction flows and API endpoints are documented within each service's subdirectory.

## Additional Setup

### Config Server

Place all configuration files in the Config Server's designated directory for centralized management.

### Service Discovery

Configure each microservice to register with the Discovery Server upon startup, enabling dynamic discovery and load balancing.

## Troubleshooting & Support

For troubleshooting:
- Check service logs for errors.
- Ensure network configurations allow for inter-service communication.
- Refer to the project's FAQ or documentation for common problem solutions.

For additional support, contact the project maintainers or utilize community forums.
