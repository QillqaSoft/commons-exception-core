package pe.quillqasoft.dev.core.catalog.auth;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;

import java.util.Map;

/**
 * Catalog for all authentication and authorization related exceptions.
 */
public enum AuthErrorCatalog implements BaseExceptionCatalog {

    /** Error indicating the user does not have required permissions (HTTP 403). */
    FORBIDDEN(
            "ERR-AUTH-403",
            "FORBIDDEN",
            "Access is denied. You do not have permission to access this resource.",
            403,
            null
    ),
    
    /** Error indicating the provided authentication token is no longer valid (HTTP 401). */
    TOKEN_EXPIRED(
            "ERR-AUTH-001",
            "TOKEN_EXPIRED",
            "The provided authentication token has expired. Please log in again.",
            401,
            null
    ),
    
    /** Error indicating an authentication context is missing or invalid (HTTP 401). */
    UNAUTHORIZED(
            "ERR-AUTH-401",
            "UNAUTHORIZED",
            "Authentication is required to access this resource.",
            401,
            null
    ),
    
    /** Error indicating the username or password provided is incorrect (HTTP 401). */
    INVALID_CREDENTIALS(
            "ERR-AUTH-002",
            "INVALID_CREDENTIALS",
            "The provided credentials are incorrect.",
            401,
            null
    );

    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final Map<String, String> errors;

    AuthErrorCatalog(String errorCode, String exceptionName, String message, int status, Map<String, String> errors) {
        this.errorCode = errorCode;
        this.exceptionName = exceptionName;
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getExceptionName() {
        return exceptionName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public Map<String, String> getErrors() {
        return errors;
    }
}
