# E-Commerce Project

Welcome to the Console-Based E-Commerce Project, an application that offers a streamlined shopping experience and efficient inventory management. Users can browse products, manage their cart, and process orders while vendors can easily handle inventory tasks.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Additional Features](#additional-features)
- [Tech Stack](#tech-stack)

## Overview

The Console-Based E-Commerce Project is a robust application that seamlessly integrates inventory management with a streamlined shopping experience. It includes an **Authentication Module** for secure user access, an **Inventory Module** for managing diverse product categories such as electronics and groceries, and a **Purchase Module** that facilitates product selection, cart management, and order processing. Additionally, the **Payment Module** ensures secure transaction processing and billing. Designed for both vendors and buyers, this project enhances the online shopping experience through an efficient console interface, combining usability and security to meet the needs of modern e-commerce.

## Features

### Authentication Management
- **User Access**: Supports secure login and registration for both vendors and buyers.
- **Login Options**: Users can log in with either username/password or mobile number.
- **Validation**: Includes validation for mobile, username, and password fields.
- **Session Management**: An authentication context stores active user details for use throughout the session.
- **Logout Option**: Allows users to securely end their session.

### Password Management
- **Robust Validation**: Ensures password compliance through matching checks and strength validation.
- **Enhanced Security**: Employs encryption for password storage and secure reset mechanisms.
- **Automated Generation**: Creates strong, randomized passwords to meet security standards when needed.

### Inventory Management
- **Inventory Access for Vendors**: Allows vendors to log in and manage products within their registered categories, such as electronics or groceries.
- **Comprehensive Product Management**: Supports adding, updating, removing, and viewing products, with options to set discounts on selected items.
- **Stock Control**: Enables vendors to adjust product quantities and manage stock levels for efficient inventory tracking.

### Product Management
- **Category-Based Organization**: Manages products across various categories, including exclusive premium products accessible only to premium users.
- **Discount and Pricing Control**: Applies and adjusts discounts for selected products.
- **User-Centric Filtering**: Provides product filters to refine search results based on user preferences.

### Cart Management
- **Flexible Cart Operations**: Allows users to add, remove, update, and view items in their cart, with options to clear the cart or view the total price.
- **Seamless Checkout**: Provides a streamlined checkout process once items are verified.
- **Cart Configuration**: Enables loading and managing the cart across different areas of the application for continuity and convenience.

### Order Management
- **Order Creation & Summary**: Converts checked-out items into an order, presenting users with an order summary for final verification.
- **Asynchronous Processing**: Queues orders and processes them asynchronously in separate threads, utilizing multithreading to enable continuous purchasing without delays.
- **Real-Time Order Status**: Updates users with the status of their order as it progresses through processing.

### Payment and Billing Management
- **Multiple Payment Options**: Facilitates various payment methods, including credit card, UPI, net banking, and cash on delivery.
- **Transaction Recording**: Securely stores transaction details for each payment made by the user.
- **Bill Generation**: Automatically generates and prints a detailed bill for every completed transaction.

## Additional Features

- **Excel-Based Data Persistence**: Utilizes Apache POI for persisting user registration details in an Excel sheet and facilitates reading and extracting data from it.
- **Global Scanner Instance**: Implements a singleton pattern for a global scanner to manage user input efficiently, allowing for consistent input retrieval and proper disposal when no longer needed.
- **Data Caching System**: Implements a robust caching solution that serializes object data into byte format for file storage, facilitating efficient retrieval through deserialization. This feature specifically optimizes the storage and access of cart configuration details, enhancing overall performance.
- **Custom Exception Handling**: Implements tailored exceptions for managing errors related to products, categories, users, and passwords, enhancing error handling and maintaining system robustness.
- **Premium User Access Control**: A custom `@Premium` annotation, dynamically managed through reflection, restricts access to exclusive products, additional discounts, wishlists, and cash-on-delivery options, enhancing the premium user experience.

## Tech Stack

- **Programming Language**: Java
- **Development Environment**: IDE - IntelliJ IDEA
- **Notable Libraries/Technologies**:
    - **Apache POI**: For Excel-based data persistence and manipulation.
    - **Java Serialization**: Utilized `ObjectOutputStream` and `ObjectInputStream` for serialization and deserialization of objects.
    - **Reflection**: Employed for dynamic class and method operations.
    - **ExecutorService**: For managing concurrency and asynchronous task execution.
    - **Java Stream API**: Used for functional-style operations on collections.
    - **BCrypt**: For password hashing and encryption via `org.mindrot.jbcrypt.BCrypt`.
