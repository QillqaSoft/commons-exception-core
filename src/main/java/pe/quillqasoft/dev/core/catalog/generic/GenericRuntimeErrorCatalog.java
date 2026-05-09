package pe.quillqasoft.dev.core.catalog.generic;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;

import java.util.Map;

/**
 * Catalog for generic runtime errors.
 */
public enum GenericRuntimeErrorCatalog implements BaseExceptionCatalog {

    /** Error indicating an unexpected internal server error (HTTP 500). */
    GENERIC_RUNTIME(
            "ERR-SYS-500",
            "INTERNAL_SERVER_ERROR",
            "An unexpected internal server error occurred. Please try again later.",
            500,
            null
    );

    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final Map<String, String> errors;

    GenericRuntimeErrorCatalog(String errorCode, String exceptionName, String message, int status, Map<String, String> errors) {
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
