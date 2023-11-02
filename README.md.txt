# Calendar Scheduler API

The **Calendar Scheduler API** is a Java Spring Boot application that provides endpoints for managing calendar events and appointments. This API makes it easy to schedule and manage events efficiently.

## API Endpoints

Here are the main endpoints provided by the API:

- **GET /schedule/listavailabilities**: Retrieve a list of availabilities.
- **POST /schedule/createavailability**: Create a new availability.
- **GET /schedule/listreservations**: Retrieve a list of reservations.
- **POST /schedule/createreservation**: Create a new reservation.
- **DELETE /schedule/deleteavailability**: Delete an availability.
- **DELETE /schedule/deletereservation**: Delete a reservation using email.

## Running the Application

1. Clone this repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Run the application.

The API will be accessible at `http://localhost:8080`. 
The Swagger UI is can be accessed at `http://localhost:8080/swagger-ui/index.html`

## Error Handling

The API includes error handling and provides informative responses when errors occur. If you encounter any issues or have questions, refer to the error messages for guidance.