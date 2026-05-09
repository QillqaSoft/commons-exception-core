package pe.quillqasoft.dev.core.catalog;

import pe.quillqasoft.dev.core.model.BaseException;

import java.util.Map;

/**
 * Interface that defines the contract for all exception catalogs.
 * It provides the base properties necessary to construct a {@link BaseException}.
 */
public interface BaseExceptionCatalog {

    /**
     * Gets the unique application-specific error code.
     *
     * @return The error code string (e.g., "ERR-SYS-500").
     */
    String getErrorCode();

    /**
     * Gets the standardized exception name, typically mapping to the exception class name.
     *
     * @return The exception name string in uppercase snake case (e.g., "INTERNAL_SERVER_ERROR").
     */
    String getExceptionName();

    /**
     * Gets the default human-readable error message.
     *
     * @return The error message.
     */
    String getMessage();

    /**
     * Gets the HTTP status code associated with this error.
     *
     * @return The integer HTTP status code (e.g., 400, 404, 500).
     */
    int getStatus();

    /**
     * Gets any predefined structural or field-specific errors associated with this catalog entry.
     *
     * @return A map of error field keys and messages, or null if there are none.
     */
    Map<String, String> getErrors();
}
