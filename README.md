# Movies App 🎬

A modern Android application that showcases popular people (actors, directors, etc.) using **The Movie Database (TMDB)** API. This app is built with a focus on clean code, scalable architecture, and modern Android development practices. 

---

## **Features**
- 📜 Displays a list of popular people with **infinite scrolling**.
- 🔍 Search for people dynamically with **search functionality**.
- 📄 View detailed information about a person (name, profile image, birthday).
- 🖼️ View images related to a person.
- 📡 **Error handling** for network failures and empty states.
- 🌙 Built using **modern Android UI principles** with Jetpack Compose.

---

## **Project Structure**

This app follows the **MVVM Architecture** with a clear separation of concerns into three layers:
### **1. Domain Layer**
- Contains the core business logic of the app.
- Includes **Use Cases** to encapsulate individual actions (e.g., fetching popular people, searching, etc.).
- Ensures the app is independent of external data sources or frameworks.

### **2. Data Layer**
- Manages the app’s data from various sources (API, local cache, etc.).
- Includes:
  - **Repository Pattern**: Centralized access point for data.
  - **Retrofit** for networking and API communication.

### **3. Presentation Layer**
- Contains UI components built with **Jetpack Compose**.
- Includes **ViewModels** to manage UI-related data and lifecycle-aware components.

---

## **Tech Stack**
This app is built using the latest technologies and best practices:
- **Kotlin**: The primary programming language.
- **Jetpack Compose**: For building declarative and responsive UI.
- **MVVM Architecture**: Ensures separation of concerns and testability.
- **Clean Architecture**: Divides the app into layers (Domain, Data, Presentation) to improve scalability and maintainability.
- **Retrofit**: For API communication and JSON parsing.
- **Coroutines**: For asynchronous programming and managing background tasks.
- **Dagger Hilt**: For dependency injection, reducing boilerplate code.
- **Coil**: For efficient image loading and caching.
- **Error Handling**: Provides meaningful error messages for API failures and empty states.
- **Secured API Key**: The API key is securely stored in `local.properties` and accessed via `BuildConfig`.

---

## **Key Design Principles**
- **Separation of Concerns (SOC)**: Each layer has a distinct responsibility, ensuring minimal coupling.
- **Scalability**: The architecture supports adding new features with minimal changes to existing code.
- **Testability**: The use of **Use Cases** and **ViewModels** ensures the app is easy to test.
- **Clean Code**: Following best practices to keep the codebase readable, maintainable, and scalable.

---

## **Folder Structure**
```plaintext
app/
├── data/
│   ├── api/                     # Retrofit API setup
│   ├── model/                   # Data models (API specific)
│   ├── repository/              # Repository layer
├── domain/
│   ├── model/                   # Domain models
│   ├── usecase/                 # Business logic encapsulated in Use Cases
├── presentation/
│   ├── components/              # Reusable UI components
│   ├── ui/                      # Screens (Popular, Search, Details)
├── di/                          # Dependency Injection (Hilt modules)
├── utils/                       # Utility classes and constants
├── MainActivity.kt              # Entry point of the app
├── App.kt                       # Application class
