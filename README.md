# Warsaw Salon Explorer
## SumUp Warsaw Accelerator Program
This is a full-stack web application designed to explore hair and beauty salons in Warsaw. It features a Spring Boot backend that fetches and manages salon data, and a React frontend for user interaction. On its initial run, the application populates a PostgreSQL database with salon data retrieved from the Google Places API.

## Features

- **Salon Discovery**: Browse through a list of salons with pagination.
- **Dynamic Search**: Instantly search for salons by name.
- **Advanced Filtering**: Filter salons by Warsaw district and minimum user rating.
- **Detailed View**: Click on a salon to see detailed information, including a photo gallery, address, contact details, and links to their website and Google Maps page.
- **Data Editing**: An edit mode allows for updating salon information, such as name, rating, district, and photos.
- **Data Synchronization**: The backend automatically fetches and stores salon data from the Google Places API upon first launch.

### Frontend
- **Main View**: A grid layout displaying salon preview cards with their name, main photo, district, and rating.
- **Filter UI**: A user-friendly dropdown for setting filters on minimum rating and selecting one or more districts.
- **Salon Details Page**: A dedicated page showing comprehensive details and a photo gallery for the selected salon.
- **Salon Edit Page**: A form to modify salon data and mark photos for removal.

### Backend
- **REST API**: A set of endpoints to serve salon data to the frontend.
- **Data Persistence**: Uses Spring Data JPA and a PostgreSQL database to store and manage salon data.
- **Google Places Integration**: A client to interact with the Google Places API for initial data fetching.
- **Specifications**: Utilizes JPA Specifications for building dynamic database queries based on user filters.

## Technology Stack

- **Backend**:
    - Java 17
    - Spring Boot
    - Spring Data JPA
    - PostgreSQL
    - Maven
- **Frontend**:
    - React
    - Vite
    - JavaScript (JSX)
    - CSS
- **API**:
    - Google Places API

## Setup and Installation

### Prerequisites

- Java 17
- Maven
- Node.js and npm
- PostgreSQL
- A valid Google Places API Key (now available in the .yaml)

### Backend Configuration

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/asadaravani/salon-explorer-sumup-task.git
    cd salon-explorer-sumup-task
    ```

2.  **Database Setup**:
    - Ensure PostgreSQL is running.
    - Create a new database named `salon_explorer`.

3.  **Configure Application Properties**:
    - Open `src/main/resources/application.yaml`.
    - Update the `spring.datasource` section with your PostgreSQL `username` and `password`.
    - Set your Google Places API key under `google.api`.

    ```yaml
    spring:
      # ...
      datasource:
        url: jdbc:postgresql://localhost:5432/salon_explorer
        username: your_username
        password: your_password
      # ...
    google:
      api: YOUR_GOOGLE_API_KEY
    ```

4.  **Run the Backend**:
    - From the root directory, run the following command:
    ```bash
    ./mvnw spring-boot:run
    ```
    The backend server will start on `http://localhost:8080`. Upon the first execution, it will sync data from the Google Places API, which may take a few moments.

### Frontend Configuration

1.  **Navigate to the Frontend Directory**:
    ```bash
    cd frontend
    ```

2.  **Install Dependencies**:
    ```bash
    npm install
    ```

3.  **Run the Frontend**:
    ```bash
    npm run dev
    ```
    The frontend development server will be accessible at `http://localhost:5173`.

## API Endpoints

The backend exposes the following REST endpoints:

| Method | Endpoint                    | Description                                                                                                   |
| :----- | :-------------------------- | :------------------------------------------------------------------------------------------------------------ |
| `GET`  | `/api/salons`               | Retrieves a paginated list of salons. Supports filtering by `districts`, `search`, `minRating`, `page`, and `size`. |
| `GET`  | `/api/salons/{id}`          | Retrieves detailed information for a single salon by its ID.                                                    |
| `PUT`  | `/api/salons/{id}`          | Updates the information for a specific salon.                                                                   |
| `GET`  | `/api/salons/districts`     | Returns a list of all available districts and the number of salons in each.                                     |

## What I'd improve with more time

While the current application fulfills the core requirements, having more time would allow me to implement several technical and feature-level enhancements:

- **Comprehensive Testing Strategy**: Implement a robust test suite to ensure application stability and prevent regressions. This would include unit and integration tests for the Spring Boot backend (using JUnit and Mockito) to verify business logic and API endpoints, as well as component testing for the React frontend (using Jest and React Testing Library) to guarantee a reliable user experience.
- **Refactor for SOLID Principles**: Conduct a comprehensive code review to ensure the backend architecture strictly adheres to SOLID principles. This would improve the long-term maintainability, testability, and scalability of the codebase.
- **Custom Pagination DTOs**: Replace Spring's default `Page` object in REST responses with a custom Pagination DTO. This would resolve current Spring stability/serialization warnings and provide a cleaner, more stable API contract for the frontend.
- **Global Exception Handling**: Implement a centralized error-handling mechanism using Spring's `@RestControllerAdvice`. This would allow the backend to catch exceptions globally and return standardized, custom error responses (via `ResponseEntity`) containing clear error codes and messages, making the API more robust and easier for the frontend to consume.- **Accessibility (a11y) and WCAG Compliance**: Audit and update the user interface to meet Web Content Accessibility Guidelines (WCAG) standards. This would involve ensuring proper semantic HTML, improving keyboard navigation, adding descriptive ARIA labels, and verifying color contrast ratios so the application is fully usable for individuals relying on screen readers or other assistive technologies.
- **UI/UX Polish**: Further enhance the user interface by adding smoother transitions, loading states (like skeleton loaders), and refining the mobile-responsive layout to provide a top-tier user experience.
- **Multi-Source Data Integration**: Expand the data sources beyond Google Places by integrating ratings and reviews from platforms like Booksy. This would give users a much more realistic and comprehensive view of a salon's reputation.
- **Geolocation & AI-Driven Recommendations**: Implement browser geolocation to show users exactly how far they are from each salon (e.g., "2.5km away"). I would also love to add an AI-analysis layer that processes recent reviews to offer smart suggestions, such as recommending the "Best overall salon within 3km based on user sentiment from the last 30 days."

