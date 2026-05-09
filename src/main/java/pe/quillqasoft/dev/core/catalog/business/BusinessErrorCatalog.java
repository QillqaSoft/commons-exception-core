package pe.quillqasoft.dev.core.catalog.business;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;

import java.util.Map;

/**
 * Catalog for business logic and domain rule violations.
 */
public enum BusinessErrorCatalog implements BaseExceptionCatalog {

    /** Error indicating a generic business rule violation (HTTP 400). */
    GENERIC_BUSINESS(
            "ERR-BIZ-400",
            "BUSINESS_RULE_VIOLATION",
            "A business rule violation occurred. Please verify your request.",
            400,
            null
    );

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
