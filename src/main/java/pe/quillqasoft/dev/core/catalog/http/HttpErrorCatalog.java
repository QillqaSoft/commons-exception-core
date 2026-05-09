package pe.quillqasoft.dev.core.catalog.http;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;

import java.util.Map;

/**
 * Catalog for HTTP and web client communication related exceptions.
 */
public enum HttpErrorCatalog implements BaseExceptionCatalog {

    /** Error indicating a failure when communicating with an external service (HTTP 502). */
    WEB_CLIENT(
            "ERR-HTTP-502",
            "EXTERNAL_SERVICE_COMMUNICATION_ERROR",
            "Error communicating with the external service. Please try again later.",
            502,
            null
    ),
    
    /** Generic HTTP error indicating an unexpected issue processing the request (HTTP 500). */
    HTTP(
            "ERR-HTTP-500",
            "GENERIC_HTTP_ERROR",
            "An unexpected HTTP error occurred while processing the request.",
            500,
            null
    ),
    
    /** Error indicating the request failed validation checks (HTTP 400). */
    VALID(
            "ERR-HTTP-400",
            "VALIDATION_ERROR",
            "The request contains invalid data. Please verify your input.",
            400,
            null
    ),
    
    /** Error indicating the requested resource could not be found (HTTP 404). */
    NOT_FOUND(
            "ERR-HTTP-404",
            "RESOURCE_NOT_FOUND",
            "The requested resource could not be found.",
            404,
            null
    );

    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final Map<String, String> errors;

    HttpErrorCatalog(String errorCode, String exceptionName, String message, int status, Map<String, String> errors) {
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
