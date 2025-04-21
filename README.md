# Sector Application

A simple web application that allows users to enter their name, select the sectors they are involved in, and agree to the terms and conditions. Submitted data is then sent to the backend for storage.

![image](https://github.com/user-attachments/assets/eeb71b10-cdc7-4852-9da0-e612c0e869ad)

## Technologies Used

**Frontend:**

* [React](https://react.dev/)
* [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
* [Material UI](https://mui.com/)

**Backend:**

* [Java](https://www.oracle.com/java/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [JPA (Java Persistence API)](https://jakarta.ee/specifications/persistence/)
* [Jackson](https://github.com/FasterXML/jackson)
* [Lombok](https://projectlombok.org/)
* [H2 Database](https://www.h2database.com/html/main.html)

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

**Run the Spring Boot application using Gradle:**

You have two options to run the backend:

* **Using the Gradle Wrapper in the terminal:** Navigate to the `backend/sector-app` folder and execute:

    ```bash
    cd backend/sector-app
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

* [Node.js](https://nodejs.org/)
* [npm](https://www.npmjs.com/) (Usually comes bundled with Node.js)

**Backend:**

* [Java Development Kit (JDK) 21 or higher](https://www.oracle.com/java/technologies/javase-downloads.html) (Ensure you have a compatible JDK installed)
* [Gradle](https://gradle.org/) (The Gradle wrapper (`gradlew` and `gradlew.bat`) is included in the project, so you typically don't need to install Gradle globally)
