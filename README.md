# OfficeData

A Java console application that manages office employee records using JDBC and MySQL. Built with Java 21 and Eclipse, the project demonstrates core database connectivity patterns — connecting to a MySQL database, performing CRUD operations on employee/office data, and handling SQL exceptions cleanly.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 (JavaSE-21) |
| Database | MySQL |
| Connectivity | JDBC (mysql-connector-j 9.7.0) |
| IDE | Eclipse |

---

## Project Structure

```
OfficeData/
├── src/                  # Java source files
├── bin/                  # Compiled class files (Eclipse output)
├── .classpath            # Eclipse classpath (includes MySQL JDBC driver)
└── .project              # Eclipse project descriptor
```

---

## Prerequisites

- Java 21 or higher
- MySQL Server (running locally or remotely)
- MySQL JDBC Driver — [mysql-connector-j 9.7.0](https://dev.mysql.com/downloads/connector/j/)
- Eclipse IDE (or any Java IDE)

---

## Setup & Run

**1. Clone the repository**
```bash
git clone https://github.com/Janhavi1214/OfficeData.git
cd OfficeData
```

**2. Set up the MySQL database**

Create a database and the required tables in MySQL:
```sql
CREATE DATABASE officedb;
USE officedb;
-- Run any table creation scripts from the project
```

**3. Configure the JDBC connection**

Update the database URL, username, and password in the source file where the connection is established:
```java
String url = "jdbc:mysql://localhost:3306/officedb";
String user = "root";
String password = "your_password";
Connection conn = DriverManager.getConnection(url, user, password);
```

**4. Add the JDBC driver to your classpath**

Download [mysql-connector-j-9.7.0.jar](https://dev.mysql.com/downloads/connector/j/) and update the `.classpath` entry to point to its location on your machine, or add it via your IDE's build path settings.

**5. Build and run**

In Eclipse: Right-click the project → Run As → Java Application.

Via command line:
```bash
javac -cp ".;path/to/mysql-connector-j-9.7.0.jar" src/*.java -d bin
java -cp "bin;path/to/mysql-connector-j-9.7.0.jar" Main
```
*(Use `:` instead of `;` on Linux/macOS)*

---

## Features

- Connect to a MySQL database using JDBC
- Perform CRUD operations on office/employee records
- Display query results in the console
- Handle SQL exceptions and connection errors

---

## Notes

- The `.classpath` file references a local path (`C:/Users/Janhavi/Downloads/...`) for the JDBC driver — update this to match your environment before running.
- `.classpath` and `.project` are Eclipse-specific files and can be ignored if using a different IDE or build tool.

---

## Author

**Janhavi** — [GitHub](https://github.com/Janhavi1214)
