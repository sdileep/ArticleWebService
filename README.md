# Article API

This service is a RESTful API with the following endpoints:
  - POST /articles : recieves oarticle data in json format and stores it within the service
  - GET /articles/{id} : returns the JSON representation of requested article
  - GET /tags/{tagName}/{date} : returns the list of articles that have that tag name on the given date and some summary data about that tag for that day

# Installation instructions
  - You would need Java 8
  - An executable jar, fairfax-article-service-0.1.0.jar, is included in the target directory of the repository. To run the service, navigate to the root (where this ReadMe exists) on your terminal and run "java -jar fairfax-article-service-0.1.0.jar" (without quotes)
  - [Optional] If you would like to build and run the service, you would need Maven, run "mvn clean package" and "java -jar target/fairfax-article-service-0.1.0.jar" (without quotes)

# Solution Description

### Technical choices
This solution uses a number of open source projects:

* Java 8 - its the latest Java version and I picked it because of familiarity
* Spring Boot - since the motive of this exercise was to evaluate on the approach, design, organisation of the code base, etc., I picked it to quickly get started on the solution.
* Spring Rest - RESTful capabilities provided by Spring. JAX-RS based implementation could have been used as well.
* Spring JPA - Spring Data JPA provides a definition to implement repositories that reference the JPA specification underneath
* H2 - In-memory database which would easily be replaced with another, in-memory or external, database.
* SLF4J/Logback - for logging.
* Maven - helps is dependency management and build
* JUnit - one of the most popular testing frameworks in Java
* MockMVC - helps in testing APIs

### Code Structure
The code is structed as follows:

* src/main/java - Standard path for java code
* src/main/resources - Standard path for resources
* src/main/test - Standard path for test cases
* com.fairfax.constants package holds all application specific constants
* com.fairfax.controller package holds all the controllers that handle application's endpoints
* com.fairfax.dto package holds all the data structures that is required but are not entities
* com.fairfax.entity package holds all the data structures that are application entities
* com.fairfax.exception package holds all the code that deals with exception and error handling
* com.fairfax.repository package holds all the code that are responsible for dealing with data layer
* com.fairfax.service package holds all the code that is responsible for business logic and is the core layer of the application. This sits in between endpoints layer and the data access layer
* com.fairfax.utility package holds all the utilities that are required by the application

### Error handling strategy
An error handling strategy should focus on handling:
* Checked exceptions - As an example, a custom ResourceNotFoundException is created and is thrown when a requested resource is not found
* Unchecked exception - In the case when there is a runtime exception, that should be handled as well

An error handling strategy should include:
* Trapping exceptions thrown in the application. RestResponseEntityExceptionHandler class takes care of trapping both checked and unchecked exceptions
* Responding to the client with an error response instead of an exception trace. RestResponseEntityExceptionHandler class uses ErrorResponse class to do the same.
* Creating layer specific exceptions. Please note that due to lack of time, this has not been done

### Testing strategy
Any testing strategy should include a way of automating execution of test cases. In the solution the test cases are created using JUnit and Mockmvc. Also, its a good practice to execute these test cases upon build to ensure that code changes did not break anything. In this solution, Maven is used to build and execute test cases. If there are any errors while executing test cases, the build is deemed a failure. Please note that although I have created a few basic test cases, its not exhaustive given the time constraint.

# Assumptions
  - Given that the specification said that data is to be persisted in the service, I've presumed that I could use an in-memory database instead of creating a custom datastructure
  - I've presumed that I could use a SQL database instead of a NoSQL one. It would have taken a lot more work in design and data modelling if a NoSQL database was used.

# Time taken
Excluding documentation, it took a little about  3.5 hours (cumulatively) to complete this exercise.

