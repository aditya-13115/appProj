# 🏦 Expenses Incomes Tracker

A simple Java application to track expenses and incomes, allowing users to add, edit, and remove entries. The application also features user registration and login functionalities.

## Features 🌟

- User registration and login 🔐
- Add, edit, and remove expense/income entries ➕✏️❌
- View balance based on entries 💰
- Data persistence using MySQL database 🗄️

## Technologies Used 🛠️

- Java Swing for GUI 🎨
- MySQL for database management 🐬
- JDBC for database connectivity 🔗

## Libraries Required 📚

- MySQL Connector/J (JDBC driver for MySQL)
- Java Development Kit (JDK) 8 or higher

## Prerequisites ⚙️

1. Install JDK (Java Development Kit) on your machine.
2. Install MySQL server and create a database named `APP_PROJ`.
3. Import the SQL commands provided below to set up the necessary tables.

### SQL Commands to Set Up Database 🗳️

```sql
CREATE DATABASE IF NOT EXISTS APP_PROJ;
USE APP_PROJ;

CREATE TABLE IF NOT EXISTS expense_income_entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    type ENUM('Income', 'Expense') NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

## Running the Application 🚀

1. Clone or download the repository.
2. Add the MySQL Connector/J JAR file to your project's classpath.
3. Open a terminal or command prompt and navigate to the project directory.
4. Compile the Java files using the following command:
   ```bash
   javac -cp .:mysql-connector-java-x.x.x.jar expense_income_tracker/*.java


