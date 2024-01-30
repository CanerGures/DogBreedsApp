# Dog Breed App

Welcome to the Dog Breed App repository! This Android application, written in Kotlin using Jetpack Compose and following a multi-module MVVM architecture with UI states, is your go-to resource for exploring and learning about various dog breeds. Whether you're a dog lover, a potential pet owner, or just curious about different breeds, this app provides a delightful experience. This app uses [Dog Breeds API](https://dog.ceo/dog-api) as the remote data source.

## 📑 Features

### 1- Breed List:
Browse through an extensive list of dog breeds, each accompanied by a captivating image and sub-breed count.

### 2- Detail Breeds:
Dive deeper into the specifics of each breed with the dedicated detail screen. Learn about characteristics, history, and more.

### 3- Favorite Breeds:
Mark your favorite breeds, creating a personalized list for quick reference.

### 4- Favorite Breeds Images:
Select your favorite breed images and save them.


## 🏁 Getting Started

### 🌏 Explore Modules

This app is separated into 3 modules:

- The ``app`` module contains the main Android application and utilizes Jetpack Compose for the user interface.
- The ``domain`` module defines the core business logic and use cases.
- The ``data`` module handles data-related operations, including fetching breed information.

## 📚 Dependencies

- [Kotlin](https://kotlinlang.org/) - The app is written in Kotlin, a modern and expressive programming language for Android development.
- [Compose](https://developer.android.com/jetpack/androidx/releases/compose) - The UI is built using Jetpack Compose, a modern Android UI toolkit that simplifies UI development.
- [Room](https://developer.android.com/training/data-storage/room) - Utilizing Room for local database storage, allowing seamless offline access to favorite breeds.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection is handled with Hilt, making the codebase more modular and maintainable.
- [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - Jetpack Navigation is employed for smooth and predictable navigation within the app.
- [Kotlinx Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Asynchronous programming is managed using Kotlin Coroutines, ensuring a responsive and efficient user experience.
- [Retrofit](https://square.github.io/retrofit/) - Networking is handled with Retrofit, making API calls to the Dog API seamlessly.
- [Moshi](https://github.com/square/moshi) - JSON parsing is performed using Moshi, providing a simple and efficient way to convert JSON to Kotlin objects.
- [Coil](https://github.com/coil-kt/coil) - Image loading and caching are managed with Coil, ensuring a smooth and efficient image viewing experience.
- [Accompanist Pager](https://google.github.io/accompanist/pager/) - Used for implementing paginated layouts, allowing users to effortlessly navigate through the breed collection.
- [Accompanist Navigation Animation](https://google.github.io/accompanist/navigation-animation/) Enhances the app's navigation experience with smooth and engaging animations.

## 🏗 Architecture

### 🕸 MVVM + Clean Architecture with Repository and Use Case Patterns

This architecture fosters separation of concerns, making it easier to understand, test, and maintain each component. The flow of data in this architecture typically follows a unidirectional pattern: View observes ViewModel, ViewModel interacts with Use Cases, Use Cases interact with Repositories, and Repositories interact with external data sources.

Dependency injection frameworks like [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) can be used to provide dependencies across modules, and the modular structure allows for independent development and testing of each module. Additionally, it provides flexibility when adapting to changes in technology or scaling the application.

![1_9fa8VrWQyNtpxvgGXghMPQ](https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/8faca63a-c913-492a-bcef-4b08d4885489)

### :floppy_disk: Data Layer
the data layer is a component or layer of the system responsible for handling the storage and retrieval of data. It acts as an intermediary between the application's business logic (or domain layer) and the underlying data storage, such as databases, web services, or other data sources. The primary purpose of the data layer is to manage data access, manipulation, and storage in a way that is efficient, scalable, and follows best practices. In the context of Android app development, the data layer often includes components like data models, data access objects (DAOs), repositories, and network or database clients. For example, this app uses [Room](https://developer.android.com/training/data-storage/room) for local database storage, the data layer consists of Room DAOs for database operations, data models representing entities, and a repository for managing data flow between the database and the rest of the application.

Separating the data layer from other components of the application (such as the presentation and domain layers) promotes a clean and modular architecture, making it easier to maintain, test, and extend the application.

### ↔ Domain Layer

In Android development, the domain layer refers to the part of the application architecture that encapsulates the business logic, rules, and entities that are specific to the application's core functionality. The domain layer is a key component of the overall architecture and is responsible for representing and managing the business domain independently of the user interface and data storage details.

### 📱 Ui Layer

In Android development, the UI (User Interface) layer refers to the part of the application responsible for presenting information to the user and capturing user input. I used  [Jetpack Compose](https://developer.android.com/jetpack/androidx/releases/compose) to implement the UI Layer.

## 🖼 Demo

### 🌞Light Theme

<p><img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/31db7620-feb2-44d9-8e53-c9bffea33220" alt="Gif2" />
<img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/e9342394-f52e-4ef8-9c3c-fc4c82017ecf" alt="Gif3" />
<img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/b061aa3e-91f7-4c0b-8f10-183e99c53ad8" alt="Gif3" />
<img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/71fb25df-c24a-492d-b08f-bbfd40401ca2" alt="Gif1" /></p>

### 🌒Dark Theme

<p><img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/dbf02105-9730-44c1-a9c1-81261ed0b0e1" alt="Gif2" />
<img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/46261558-2821-4b47-8977-431cfbe811e6" alt="Gif3" />
<img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/a1de5e96-fcf0-488e-adbf-1176c7c4b07a" alt="Gif3" />
<img height= "450" src="https://github.com/shape-interviews/android-take-home-CanerGures/assets/24733838/034aada8-50e9-4d32-9fdb-22b8b7e25ca9" alt="Gif1" /></p>
