# Sector Application

A simple web application that allows users to enter their name, select the sectors they are involved in, and agree to the terms and conditions. Submitted data is then sent to the backend for storage.

![image](https://github.com/user-attachments/assets/eeb71b10-cdc7-4852-9da0-e612c0e869ad)

## Technologies Used

**Frontend:**

* [React](https://react.dev/) - A JavaScript library for building user interfaces.
* [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript) - The primary programming language for the frontend.
* [Material UI](https://mui.com/) - A popular React UI framework implementing Google's Material Design.

**Backend:**

* [Java](https://www.oracle.com/java/) - The primary programming language for the backend.
* [Spring Boot](https://spring.io/projects/spring-boot) - A framework that simplifies the development of stand-alone, production-grade Spring-based Applications.
* [JPA (Java Persistence API)](https://jakarta.ee/specifications/persistence/) - A specification for managing relational data in Java applications.
* [Jackson](https://github.com/FasterXML/jackson) - A high-performance JSON processing library for Java (used for serializing and deserializing data).
* [Lombok](https://projectlombok.org/) - A Java library that reduces boilerplate code (like getters, setters, constructors) using annotations.
* [H2 Database](https://www.h2database.com/html/main.html) - An in-memory relational database, primarily used for development and testing.

## How to Run the Application

Both the frontend and backend applications need to be running simultaneously for the application to function correctly.

**Frontend Setup:**

1.  **Navigate to the frontend folder** in your terminal:

    ```bash
    cd frontend
    ```

2.  **Install the necessary dependencies:**

    ```bash
    npm install
    ```

3.  **Start the frontend development server:**

    ```bash
    npm run dev
    ```

    This command will typically start the React development server, and the application will be accessible in your web browser (usually at `http://localhost:3000`).

**Backend Setup:**

1.  **Navigate to the backend root folder:**

    ```bash
    cd backend
    ```

2.  **Run the Spring Boot application using Gradle:**

    You have two options to run the backend:

    * **Using the Gradle Wrapper in the terminal:** Navigate to the `backend` folder and execute:

        ```bash
        ./gradlew bootRun
        ```

        (On Windows, use `gradlew.bat bootRun`)

    * **Using IntelliJ IDEA:**
        1.  Import the `backend` folder as a Gradle project in IntelliJ.
        2.  Once the project is imported, you can find the Gradle tasks in the Gradle tool window (usually on the right side).
        3.  Navigate to `Tasks` -> `application` -> `bootRun` and double-click it to run the backend.

    The backend server will typically start on port `8080`. You will see logs in the terminal indicating the application startup.

**Accessing the Application:**

Once both the frontend and backend servers are running, open your web browser and navigate to the address where the frontend is served (usually `http://localhost:3000`). You should now be able to interact with the Sector Application.

## Requirements

**Frontend:**

* [Node.js](https://nodejs.org/) (Make sure you have Node.js installed on your system)
* [npm](https://www.npmjs.com/) (Usually comes bundled with Node.js)

**Backend:**

* [Java Development Kit (JDK) 21 or higher](https://www.oracle.com/java/technologies/javase-downloads.html) (Ensure you have a compatible JDK installed)
* [Gradle](https://gradle.org/) (The Gradle wrapper (`gradlew` and `gradlew.bat`) is included in the project, so you typically don't need to install Gradle globally)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Optional, but recommended for backend development)
