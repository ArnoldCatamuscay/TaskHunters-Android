# TaskHunters
TaskHunters is an Android application developed with **Jetpack Compose** and **Firebase** for user authentication.

`Android Studio` `Kotlin` `Jetpack Compose` `Firebase`

## Description
TaskHunters is a mobile application designed to help you manage your habits by creating tasks, which can be separated into **Dailies** (daily tasks) and **To Do's** (tasks to be done). Created tasks are saved using **Room** for local persistence. The application has **Firebase** integration for user registration and login, allowing users to use the application without the need to register thanks to anonymous authentication.

>### 1. Set up your Firebase project
   - Go to the [**Firebase console**](https://console.firebase.google.com/u/0/) and create a new project.
   - Go to `Compilation` -> `Authentication` -> `Get started` -> `Access methods` then enable `anonymous` authentication and `email/password` authentication.
   - Disable the email enumeration protection under `Authentication` -> `Settings` -> `User actions`  (keeping it enabled will prevent testing user registration).
   - Associate the Android app with the Firebase project. The Android package name for this project is `co.unicauca.taskhunters`, which can be found in the `build.gradle.kts` file (at module level) as the value of the `applicationId` keyword.
   
   - Download the `google-services.json` file (ignore the step of adding the SDK and click next and then go to console).

>### 2. Add the JSON file to the project
   - Open the project in Android Studio.
   - Switch to the `project view`.
   - Paste the downloaded `google-services.json` file into the `app` directory.

>### 3. Run the project
   - Unistall the app in the Android Studio Emulator if you have a old version.
   - Run the project.
