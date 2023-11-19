# SE PROTOTYPE

  This program is part of the ITCS371_Introduction to Software Engineering project. Our group, Ninthera, has written an Android app as a simple prototype to prove the concept of DFD level 0, 1, and 2.

## Table of Contents

- [MainActivity.kt](https://github.com/WhySoDk/SEPrototype/blob/master/app/src/main/java/com/example/prototype2/MainActivity.kt)
- [Registration activity](https://github.com/WhySoDk/SEPrototype/blob/master/app/src/main/java/com/example/prototype2/RegistrationActivity.kt)
- [Menu activity](https://github.com/WhySoDk/SEPrototype/blob/master/app/src/main/java/com/example/prototype2/HubActivity.kt)
- [Add Appointment activity](https://github.com/WhySoDk/SEPrototype/blob/master/app/src/main/java/com/example/prototype2/AddAppointmentActivity.kt)
- [View Appointment activity](https://github.com/WhySoDk/SEPrototype/blob/master/app/src/main/java/com/example/prototype2/ViewAppointmentActivity.kt)
- [Change Appointment activity](https://github.com/WhySoDk/SEPrototype/blob/master/app/src/main/java/com/example/prototype2/ChangeAppointmentDateActivity.kt)

## Features

Our prototype is capable of registering new users, storing their passwords, and managing a list of appointments 
with add and change features. The database is stored locally using Android's built-in SQLite. For all user inputs, 
we have implemented regex and data checking to prevent input errors.

## Note

When using the date input box, please note that some soft keyboards, such as the Samsung keyboard, may not provide a slash ("/"), which is required for our date format. To ensure accurate testing of the prototype, we recommend downloading and using Gboard, a soft keyboard from Google, which supports the necessary date format.


## Installation
  
Make sure you have the following installed on your machine:

- [Android Studio](https://developer.android.com/studio)
- Android SDK with necessary components

## Running the App

  1. Open the project in Android Studio.
  2. Connect an android device using a USB debugger or start an emulator.
  3. Click the "Run" button in Android Studio to build and run the project.

### Clone the Repository

  Clone the repository to your local machine using the following command:
  
    git clone https://github.com/WhySoDk/SEPrototype.git
