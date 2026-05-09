# Commons Exception Core

![Java 21](https://img.shields.io/badge/Java-21-blue.svg)
![JitPack](https://jitpack.io/v/QillqaSoft/commons-exception-core.svg)
![Build Status](https://github.com/QillqaSoft/commons-exception-core/actions/workflows/ci.yml/badge.svg)

This library provides a robust, standardized, and highly maintainable way to handle exceptions across Java applications. By leveraging a **"Convention over Configuration"** approach, it eliminates boilerplate code, keeping exception classes completely clean while automatically binding them to centralized error catalogs.

### Key Benefits

* **Zero Boilerplate:** Exception classes require no constructors or logic.
* **Immutable State:** Exception attributes are strictly `final`.
* **Centralized Catalogs:** Error definitions are grouped by domain using Enums.
* **Fluent Builder:** Easily override specific attributes dynamically without cluttering the base class.

---

## Core Components

* **`BaseException`**: The engine of the library. It is an abstract class that intercepts instantiation, reads the class metadata, and automatically resolves properties from the catalog using reflection and thread-local builders.
* **`BaseExceptionCatalog`**: The contract for exception metadata. It enforces that all error catalogs provide an `errorCode`, `exceptionName`, `message`, `status`, and `errors` map.
* **`@ExceptionDef`**: The declarative binding annotation. Placed on an exception class, it points the engine to the specific Enum catalog where the error details are stored.

---

## How to Create a New Exception

Creating a new exception is a highly streamlined two-step process.

### 1. Define the Error in a Catalog (Enum)

Catalogs group related exceptions by domain (e.g., Auth, Business, Database). An Enum catalog must implement `BaseExceptionCatalog`.

> **IMPORTANT: Naming Convention**
> The Enum constant name **must** mathematically match the exception class name converted to `UPPER_SNAKE_CASE`, ignoring the "Exception" suffix.

```java
package pe.quillqasoft.dev.core.catalog.business;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;
import java.util.Map;

public enum BusinessErrorCatalog implements BaseExceptionCatalog {

    // Maps directly to UserNotFoundException
    USER_NOT_FOUND(
            "ERR-BUS-001",
            "USER_NOT_FOUND",
            "The requested user could not be found in the system.",
            404,
            null
    ),
    
    // Maps directly to InvalidOperationException
    INVALID_OPERATION(
            "ERR-BUS-002",
            "INVALID_OPERATION",
            "This operation is not permitted.",
            400,
            null
    );

    // --- Standard Enum Boilerplate ---
    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final Map<String, String> errors;

    BusinessErrorCatalog(String errorCode, String exceptionName, String message, int status, Map<String, String> errors) {
        this.errorCode = errorCode;
        this.exceptionName = exceptionName;
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    @Override public String getErrorCode() { return errorCode; }
    @Override public String getExceptionName() { return exceptionName; }
    @Override public String getMessage() { return message; }
    @Override public int getStatus() { return status; }
    @Override public Map<String, String> getErrors() { return errors; }
}

```

### 2. Create the Exception Class

Create a class extending `BaseException`. Annotate it with `@ExceptionDef` pointing to the catalog class. No constructors are needed.

```java
package pe.quillqasoft.dev.exception.business;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.business.BusinessErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

@ExceptionDef(BusinessErrorCatalog.class)
public class UserNotFoundException extends BaseException {
    
    // Optional builder
    public static Builder<UserNotFoundException> builder() {
        return new Builder<>(UserNotFoundException.class);
    }
}
```

---

## Usage Guide

You have two ways to throw exceptions depending on your context.

**A. Direct Throw (Static Defaults)**
Use this when the default catalog message is sufficient. The engine automatically populates all `final` fields.

```java
throw new UserNotFoundException();
```

**B. Builder Throw (Dynamic Context)**
Use this when you need to provide contextual information, such as dynamic IDs or validation maps.

```java
throw UserNotFoundException.builder()
    .withMessage("User with email 'admin@test.com' was not found.")
    .withErrors(Map.of("email", "Not found in database"))
    .build();
```

---

## Naming Convention Engine

To keep classes devoid of logic, `BaseException` uses reflection to find the enum constant automatically. If the convention is broken, the engine will fail-fast and throw an `IllegalStateException` at runtime.

**The Resolution Algorithm:**

1. Read the Exception Class Name.
2. Strip the `Exception` suffix (if present).
3. Convert CamelCase to `UPPER_SNAKE_CASE`.
4. Find the matching constant in the linked `@ExceptionDef` catalog.

| Exception Class Name | Stripped Name | Required Enum Constant |
| --- | --- | --- |
| `UserNotFoundException` | `UserNotFound` | `USER_NOT_FOUND` |
| `InvalidCredentialsException` | `InvalidCredentials` | `INVALID_CREDENTIALS` |
| `WebClientException` | `WebClient` | `WEB_CLIENT` |
| `GenericBusinessException` | `GenericBusiness` | `GENERIC_BUSINESS` |

---

## Project Structure

* `core.model.BaseException`
  The core engine managing instantiation and immutability.
* `core.annotation.ExceptionDef`
  The declarative link between classes and catalogs.
* `core.catalog.*`
  Interfaces and Enum catalogs grouping business rules.
* `exception.*`
  Clean, one-line exception classes grouped by domain.