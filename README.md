# User Registration and Authentication Flow - Daily Rounds/Marrow Assignment

This repository contains the Android application that implements the user registration and authentication flow for the Daily Rounds/Marrow assignment. The app follows the MVVM architectural pattern and uses ViewModel for managing the UI-related data. Dagger is used for dependency injection to manage dependencies for Room DB and Retrofit.

## Objective

The objective of this assignment is to design and implement a user registration and authentication flow in an Android application using ViewModel and MVVM architecture. The flow includes three activities: Login Activity, Registration Activity, and Dashboard Activity.

## Features

The application includes the following features:

1. **Login Activity:** Upon opening the app, the user is presented with the Login Activity. The app checks if there is any user data present in the preferences. If the last login status is "yes," the user is automatically forwarded to the Dashboard Activity. Otherwise, the user needs to enter their email and password to log in.

2. **Dashboard Activity:** If the user is successfully logged in or the last login status is "yes," they are directed to the Dashboard Activity. This screen displays all the details of the user retrieved from Room Database, including their email, name, and selected country. The Dashboard Activity also contains a logout button to log the user out and return them to the Login Activity.

3. **Registration Activity:** If the user clicks on the "Register" button on the Login Activity, they are redirected to the Registration Activity. In this screen, the user is required to provide their email, password, and confirm password. Additionally, they need to select their country from a dropdown list. The app performs all necessary validations to ensure correct user input. If all validations pass, the user account is created, and the data is saved in Room Database.

## Requirements

The app fulfills the assignment requirements as follows:

1. User Registration: The Registration Activity allows users to register by providing their email, password, and country information.

2. Password Validation: The app validates the password input during registration to ensure it meets the specified requirements (minimum 8 characters, at least one number, special characters [!@#$%&()], one lowercase letter, and one uppercase letter).

3. Country List: The list of countries is read from the provided JSON file using Retrofit, and users can select their country from a dropdown list during registration.

4. User Authentication: The Login Activity handles user authentication by checking the last login status in preferences and verifying the entered credentials.

5. Last Login Status: The last login status (yes or no) is saved in preferences to determine whether the user is logged in or needs to log in.

6. Logout: The Dashboard Activity includes a logout button, which allows users to log out and return to the Login Activity.

## Technologies and Libraries Used

- Kotlin: The app is developed using the Kotlin programming language.
- ViewModel: ViewModel is used to manage and store UI-related data.
- MVVM Architecture: The app follows the Model-View-ViewModel architectural pattern to separate concerns and improve code maintainability.
- Datastore Preferences: Used for storing and retrieving the last login status and last login ID.
- Room Database: Used for storing and retrieving user details such as email, name, and country information with Dagger for dependency injection.
- Retrofit: Used for handling API calls to retrieve the list of countries.
- Dagger: Used for dependency injection, managing dependencies for Room DB and Retrofit.

## Running the Application

To build and run the application, follow these steps:

1. Clone the repository to your local machine using the following command:
```
git clone https://github.com/kartikeysaran/DailyRoundsTechAssignment
```

2. Open the project using Android Studio.

3. Build and run the application on an Android emulator or a physical device.

## Implementation Details

- The app follows the MVVM architectural pattern, separating the UI components from the business logic.
- ViewModel is used to manage the data and communication between the UI and data layer.
- Dependency injection with Dagger is used to manage dependencies for Room DB and Retrofit.
- The Registration Activity performs all necessary validations for email, password, and country selection before creating a new user account in Room Database.
- The Login Activity checks the last login status in preferences to determine whether the user is logged in or needs to log in.

## Summary of Design Choices

- ViewModel and MVVM architecture were chosen to promote code separation and maintainability.
- Datastore Preferences were used for efficient and simple storage of the last login status and last login ID.
- Room Database was used for storing and retrieving user details with Dagger for dependency injection.
- Retrofit was used for making API calls to retrieve the list of countries.

## Additional Notes

I thoroughly enjoyed working on this assignment and appreciate the opportunity to showcase my Android development skills. I am excited to discuss my solution and design choices during the evaluation process.

If you have any questions or need further clarification, please feel free to reach out.

Thank you!

---
