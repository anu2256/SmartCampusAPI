Smart Campus API
Overview
The Smart Campus API is a RESTful service developed to manage campus resources, specifically focusing on building/room management, 
sensor monitoring, and data collection. The API allows administrators to track campus environmental data efficiently while ensuring data integrity through validation rules.

Key Features:

Room Management: Add and delete rooms.

Sensor Management: Register sensors with specific types and associate them with rooms.

Reading Logs: Capture sensor data readings while enforcing status-based restrictions (e.g., maintenance mode).

Validation: Prevents invalid associations (e.g., adding sensors to non-existent rooms) and handles conflicts during resource deletion.

Prerequisites
Java Development Kit (JDK) 8 or higher

NetBeans IDE

GlassFish/Payara Server (or any JAX-RS compatible application server)

Maven (for dependency management)

How to Build and Launch
Clone the Repository:
git clone https://github.com/anu2256/SmartCampusAPI.git

Open in NetBeans:

Launch NetBeans.

Go to File > Open Project and select the folder where you cloned the repository.

Build:

Right-click the project in the Projects window.

Select Clean and Build.

Launch:

Right-click the project and select Run.

The application will deploy to your local server.

The API base URL will be: http://localhost:8080/smart-campus-api/api/v1/

Sample API Requests (cURL)
Below are examples of how to interact with the API using curl.

1. Create a New Room
curl -X POST http://localhost:8080/smart-campus-api/api/v1/rooms \
     -H "Content-Type: application/json" \
     -d '{"name": "Lab Room 01"}'

2. Add a New Sensor

curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors \
     -H "Content-Type: application/json" \
     -d '{"roomId": "1", "type": "Temperature"}'

3. Update Sensor Status (Maintenance Mode)
   
curl -X PUT http://localhost:8080/smart-campus-api/api/v1/sensors/1 \
     -H "Content-Type: application/json" \
     -d '{"status": "MAINTENANCE"}'

4. Post a Sensor Reading

curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors/1/readings \
     -H "Content-Type: application/json" \
     -d '{"value": 25.5}'
5. Get All Sensors

curl -X GET http://localhost:8080/smart-campus-api/api/v1/sensors

6. Get All Rooms

curl -X GET http://localhost:8080/smart-campus-api/api/v1/rooms

6. Get Readings for a Specific Sensor
   
curl -X GET http://localhost:8080/smart-campus-api/api/v1/sensors/1/readings

7.Delete a Room
To remove a room from the system:

curl -X DELETE http://localhost:8080/smart-campus-api/api/v1/rooms/1

8. Update Sensor Status (Maintenance Mode)
Update the status of a specific sensor to 'MAINTENANCE'.

curl -X PUT http://localhost:8080/smart-campus-api/api/v1/sensors/{id} \
     -H "Content-Type: application/json" \
     -d '{"status": "MAINTENANCE"}'

Error Handling & Edge Cases

This API includes validation to ensure data integrity. Below are examples of how the system handles invalid requests:

### 1. 422 Unprocessable Entity (Invalid Room)
Triggered when attempting to add a sensor to a non-existent room.

curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors \
     -H "Content-Type: application/json" \
     -d '{"roomId": 999, "type": "Temperature"}'
2. 403 Forbidden (Maintenance Mode)
Triggered when trying to add a reading to a sensor that is in 'MAINTENANCE' status.

First, update status to MAINTENANCE
curl -X PUT http://localhost:8080/smart-campus-api/api/v1/sensors/1 \
     -H "Content-Type: application/json" \
     -d '{"status": "MAINTENANCE"}'

# Then, attempt to post reading (will return 403)
curl -X POST http://localhost:8080/smart-campus-api/api/v1/sensors/1/readings \
     -H "Content-Type: application/json" \
     -d '{"value": 25.5}'

409 Conflict (Dependency Constraint)
Triggered when attempting to delete a room that still has active sensors assigned to it.


curl -X DELETE http://localhost:8080/smart-campus-api/api/v1/rooms/1


## 2. Conceptual Report

### Part 1: Service Architecture & Setup

**Q: Explain the default lifecycle of a JAX-RS Resource class and its impact on data management.**
By default, JAX-RS resources follow a "request-scoped" lifecycle, where a new instance is created for each HTTP request and destroyed afterwards.
Because these instances are ephemeral, we use the Singleton pattern for the DataStore to ensure data persists throughout the application's lifecycle.
To prevent race conditions in this Singleton, we use thread-safe collections like `ConcurrentHashMap` or synchronized methods.

**Q: Why is Hypermedia (HATEOAS) a hallmark of advanced RESTful design?**
HATEOAS ("Hypermedia as the Engine of Application State") moves control to the server, allowing the API to evolve without breaking client implementations.
It provides dynamic discovery, allowing clients to "discover" functionality via links at runtime, making the API self-documenting.

### Part 2: Room Management

**Q: Implications of returning IDs versus full room objects?**
Returning only IDs reduces network bandwidth, which is ideal for low-bandwidth environments like mobile devices.
However, it can lead to the "N+1" problem where the client must perform multiple follow-up GET requests to get details.
We recommend returning a "Summary" object (ID and Name) for better balance.

**Q: Is the DELETE operation idempotent?**
Yes, the DELETE operation is idempotent.
While the first request returns a `204 No Content`, subsequent requests return `404 Not Found`, but the end state of the server remains identical (the resource is gone).

### Part 3: Sensor Operations & Linking

**Q: Consequences of media type mismatch in @Consumes?**
If the client sends data in an unsupported format (e.g., `text/plain`), the JAX-RS container rejects the request and returns a `415 Unsupported Media Type` status code.
This protects the system from `JsonParseException` errors.

**Q: Why is @QueryParam superior to path parameters for filtering?**
Query parameters are optional, allowing a single endpoint to handle both "all resources" and "filtered resources".
Path parameters create too many combinations (e.g., `/sensors/type/CO2/status/ACTIVE`), making the API hard to maintain.

### Part 4: Deep Nesting with Sub-Resources

**Q: Benefits of the Sub-Resource Locator pattern?**
It enforces the Single Responsibility Principle (SRP) by delegating specific path logic (like readings) to dedicated classes (e.g., `SensorReadingResource`).
It avoids "God Objects" (massive controllers), improves testability, and allows for cleaner encapsulation of business logic.

### Part 5: Error Handling & Security

**Q: Why is HTTP 422 more semantically accurate than 404 for missing payload references?**
404 implies the URI itself does not exist.
422 is more accurate when the URI is correct, but the business logic in the payload (like an invalid `roomId`) is flawed, allowing for a distinction between broken links and broken business rules.

**Q: Risks of exposing stack traces?**
It creates an Information Disclosure (CWE-209) vulnerability.
Attackers can identify the technology stack, library versions, internal infrastructure paths, and specific line numbers to exploit logic flaws.

**Q: Why use JAX-RS filters instead of manual logging?**
Filters ensure consistency (DRY principle) and separation of concerns.
  They keep the resource methods clean by moving "noise" (logging logic) into a separate, maintainable component.















