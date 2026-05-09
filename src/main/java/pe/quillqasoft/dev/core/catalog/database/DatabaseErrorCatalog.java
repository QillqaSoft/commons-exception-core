package pe.quillqasoft.dev.core.catalog.database;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;

import java.util.Map;

/**
 * Catalog for database related exceptions.
 */
public enum DatabaseErrorCatalog implements BaseExceptionCatalog {

    /** Error indicating a generic database access error (HTTP 500). */
    GENERIC_DATABASE(
            "ERR-DB-001",
            "DATA_ACCESS_ERROR",
            "A data access error occurred. The database operation could not be completed.",
            500,
            null
    );

    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final Map<String, String> errors;

    DatabaseErrorCatalog(String errorCode, String exceptionName, String message, int status, Map<String, String> errors) {
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
