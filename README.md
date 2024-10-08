# Songify

Songify is a music data management application that handles information about artists, albums, genres, and songs. It is written in Java using the Spring framework and implements CRUD (Create, Read, Update, Delete) operations, allowing users to easily add, update, retrieve, and delete music-related information.

## Features

### Artists
- **Add new artists:** With an option to include a default album and song.
- **Update artist names:** Change the name of an existing artist.
- **Delete artists:** Remove an artist along with their albums and songs.
- **Retrieve artists:** Get a paginated list of artists.

### Albums
- **Add albums:** Create new albums and assign songs to them.
- **Assign artists to albums:** Link artists to specific albums.
- **Retrieve album details:** Get detailed information about albums, including associated artists and songs.

### Genres
- **Add new genres:** Add different music genres to the database.

### Songs
- **Add new songs:** Add new songs to the collection.
- **Update songs:** Update and partially update existing songs.
- **Delete songs:** Remove songs from the collection.
- **Retrieve songs:** Get a paginated list of songs and search for songs by ID.

## How It Works

The program uses the "facade" design pattern to consolidate the functionalities of various services into a single interface. This makes managing music data simple and cohesive. All operations are executed within a transactional context, ensuring data integrity and safety.

## Technologies Used

- **Java 17**
- **Spring Boot 3.1.0** - Core framework
- **Spring MVC** - Web framework
- **Spring Data JPA** - Data persistence
- **Spring Boot Starter Thymeleaf** - Server-side templating
- **Hibernate** - ORM for data handling
- **PostgreSQL** - Database
- **Flyway** - Database migrations
- **Lombok** - Boilerplate code reduction
- **Springdoc OpenAPI** - Swagger integration for API documentation
- **JUnit 5** - Testing framework
- **AssertJ** - Fluent assertions for testing
- **Maven** - Build and dependency management

## Getting Started

1. **Clone the repository:**
    ```bash
    git clone https://github.com/NartiOfficial/Songify.git
    ```
2. **Navigate to the project directory:**
    ```bash
    cd Songify
    ```
3. **Build and run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

## Future Improvements

- **Enhance Testing:**
  - Add integration tests to verify interactions between different components.
  - Add unit tests to ensure individual components and methods function as expected.

- **Introduce Spring Security:**
  - Implement security features to manage authentication and authorization, securing access to various endpoints.
