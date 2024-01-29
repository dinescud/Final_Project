# Pet Shop Management System

## Description

This project is a Pet Shop Management System that provides functionalities for managing products, staff, and store animals. It allows users to add, remove, and sort products, as well as handle client and owner information. The system supports file handling for reading and writing data, sorting and grouping of products, and includes robust exception handling and input validation.

## Table of Contents

- [Features](#features)
- [How to Install and Run the Project](#how-to-install-and-run-the-project)
- [Classes](#classes)
- [Interfaces](#interfaces)
- [Exceptions](#exceptions)

## Features

### Store Management
- Add, remove, and sort(by price/weight) animals.

### Staff Management
- Handle staff information(name, age, job).

### File Handling
- Read and write data to files.

### Sorting and Grouping
- Sort and group animals.

### Exception Handling
- Handle exceptions related to animal weight and staff age.

### Input Validation
- Validate user input.

## How to Run the Project

### Prerequisites

Before running the application, ensure that you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- Git (optional for cloning the repository)


### Running the Application

After running the application, follow the command prompts:

- To create a store from scratch or use an old one:

    ```bash
    1. Start a new inventory           
  2. Continue with old inventory
    ```
  After executing this command, if the second choice is selected, the program will prompt you to enter the file name (including the extension) to load the previous state.


- Different functionalities:

    ```bash
    1. Add animal
  2. Remove animal
  3. Print store
  4. Compute total weight of animals
  5. Count animals
  6. Group animals by breed
  7. Exit
    ```


## Classes

### `Store`
- Represents the store.
- Manages a list of animals and a staff member.
- Provides methods for adding and removing animals.
- Implements functionality for counting and sorting animals.

### `Staff`

- Represents the staff.
- Implements `Comparable<Staff>` interface.
- Manages staff information such as name, job, and age.
- Provides methods for checking and validating age.

### `Animal`

- Represents a generic animal.
- Implements `needGroom`, `changeWater`, `Comparable<Animals>` interfaces.
- Manages animal attributes such as breed, price, age and weight.
- Implements functionalities for sorting, comparing and grouping animals by breed.
- Provides methods for checking and validating animal weight.

### `Dog`

- Subclass of `Animal` representing dogs.
- Manages specific attributes for breeds.
- Implements `needGroom` interface.

### `Fish`

- Subclass of `Animal` representing fishes.
- Manages specific attributes for breeds.
- Implements `changeWater` interface.

### `Application`

- Used to test the functionality of the `Store` and `Animal` classes and their methods.
- Implements functions for creating an inventory, adding animals, removing animals, and testing various operations.
- Manages user input.

### `InputDevice`

- Manages file input, and random animal generation.
- Implements functionality for reading staff information and animals from files.

### `OutputDevice`

- Manages output to the console and file output.
- Implements functionality for printing store information and writing store data to a file.

### `Main`

- Main class for the application that creates a store and runs it.
- Implements functionality to start the program.

## Interfaces

### `needGroom`

- Interface for checking the status of a dog's groom.
- Includes methods for checking and validating dog attributes.

### `changeWater`

- Interface for checking the water in the fishbowl.
- Includes methods for checking and validating fish attributes.

## Exceptions

### `InvalidAnimalWeight`

- Thrown for invalid animal weight.

### `InvalidPersonAge`

- Thrown for invalid person age.