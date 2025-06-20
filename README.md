# 📊 REST Assured API Automation Projects

This repository contains multiple **REST Assured** projects used for automating RESTful APIs. Each project is modular, self-contained, and follows best practices for maintainability, readability, and reusability.

---

## 🔧 Tech Stack

* Java 17+
* REST Assured
* TestNG 
* Jackson  (for JSON serialization)
* Maven
* Log4j 
* Extent Reports 
* Git

---



---

## 🔄 Common Features in All Projects

* Token-based authentication support
* Request/response logging
* Custom base class for request setup
* Externalized test data using JSON
* Custom reusable methods for GET, POST, PUT, DELETE
* Assertion wrappers with logging
* Configurable status code checks

---

## 📖 Getting Started

### 1. Clone the Repository
### 2. Build the Project

```bash
mvn clean install
```

### 3. Run Tests

```bash
mvn test
```

---

## 🔐 Authentication Support

Each project supports:

* Basic Auth
* Bearer Token (JWT)
* OAuth 2.0 

Add tokens via headers in the base test class:

```java
.header("Authorization", "Bearer " + token)
```

---

## 🔍 Reporting

* **Extent Reports**  integrated.
* Reports are automatically generated in `/test-output` or `/allure-results`.

---





