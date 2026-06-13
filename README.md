
# 🚗 Parking-Project

A lightweight, terminal-based Parking Management System built completely from scratch using **native Java APIs** and the **Jackson** library. 

This project was developed strictly for **educational purposes** to practice building, exposing, and consuming a clean REST API without relying on modern heavy frameworks (like Spring Boot).

---

## 🛠️ Architecture & Design

The project strictly follows the **MVC (Model-View-Controller)** architectural pattern to ensure a clean separation of concerns:

### 🚀 Backend Layers
* **Models**: Features an abstract base class `Vehicle` which enforces core attributes and behaviors (like entry time and value calculation), extended by specific implementations: `Car` and `Motorcycle`.
* **Service**: Handles the core business rules, such as tracking parked vehicles and calculating total amounts due upon exit.
* **Controller**: Exposes HTTP endpoints using Java's native routing capabilities to handle incoming requests and map responses.

### 💻 Frontend Layer
* **Views (CLI)**: A command-line interface (`CliView`) that runs an interactive loop in the terminal. It acts as an independent client, consuming the backend API via Java's native `HttpClient`.

---

## 🔌 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/vehicles` | List all parked vehicles |
| **POST** | `/api/vehicles` | Register/Check-in a new vehicle |
| **GET** | `/api/vehicles/{id}` | Get the payment value for a specific vehicle |
| **DELETE** | `/api/vehicles/{id}` | Remove/Check-out a vehicle from the parking lot |

### 📥 POST Request Body Example (`/api/vehicles`)

When creating a new vehicle, the API expects a JSON payload matching the following structure:

```json
{
  "licensePlate": "ABC-124",
  "model": "Trail",
  "typeVehicle": "MOTORCYCLE",
  "engineDisplacement": 160
}

```

> *Note: The `engineDisplacement` field is specific to `MOTORCYCLE` types and can be omitted or sent as null for `CAR` types.*

---

## 🏃 How to Run the Application

Because this project isolates the server host from the client terminal view, you must start them in a specific order:

1. **Start the Server:** Locate and run the **`Main`** class. This initializes the native HTTP server on `localhost:8080` to start listening for API requests.
2. **Start the Client Interface:** Locate and run the **`CliView`** class. This will boot up the command-line interface menu in your terminal, allowing you to seamlessly interact with and consume the live API.

---

Developed for learning purposes, deep-diving into Java's native HTTP handling, I/O streams, and JSON serialization.
