package pe.quillqasoft.dev.core.model;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;
import pe.quillqasoft.dev.core.annotation.ExceptionDef;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Base abstract class for all custom exceptions within the application.
 * <p>
 * This class uses reflection and a custom annotation ({@link ExceptionDef}) to automatically
 * bind subclass instances to a specific catalog entry ({@link BaseExceptionCatalog}). 
 * It populates standard API error properties such as error codes, messages, and HTTP statuses.
 * </p>
 */
public abstract class BaseException extends RuntimeException {

    /** The specific error code identifier (e.g., ERR-123). */
    private final String errorCode;

    /** The normalized exception name (typically SNAKE_CASE version of the class name). */
    private final String exceptionName;

    /** The descriptive error message intended for developers or logging. */
    private final String message;

    /** The suggested HTTP status code associated with the error. */
    private final int status;

    /** The timestamp when this exception was instantiated. */
    private final LocalDateTime timestamp;

    /** An optional map containing detailed field-level validation errors. */
    private final Map<String, String> errors;

    /** Thread-local storage to pass builder properties safely to the no-args constructor via reflection. */
    private static final ThreadLocal<Builder<?>> CURRENT_BUILDER = new ThreadLocal<>();

    /**
     * Protected constructor that initializes exception properties.
     * <p>
     * If called via the Builder pattern, it injects properties defined by the builder.
     * Otherwise, it uses reflection to read the default properties from the catalog
     * mapped by the {@link ExceptionDef} annotation.
     * </p>
     *
     * @throws IllegalStateException If the {@link ExceptionDef} annotation is missing or misconfigured.
     */
    protected BaseException() {
        super();
        this.timestamp = LocalDateTime.now();

        Builder<?> builder = CURRENT_BUILDER.get();

        if (builder != null) {
            this.errorCode = builder.errorCode;
            this.exceptionName = builder.exceptionName;
            this.message = builder.message;
            this.status = builder.status;
            this.errors = builder.errors;
        } else {
            ExceptionDef def = this.getClass().getAnnotation(ExceptionDef.class);
            if (def != null) {
                BaseExceptionCatalog catalog = findCatalog(this.getClass(), def);
                this.errorCode = catalog.getErrorCode();
                this.exceptionName = catalog.getExceptionName();
                this.message = catalog.getMessage();
                this.status = catalog.getStatus();
                this.errors = catalog.getErrors();
            } else {
                throw new IllegalStateException("Missing @ExceptionDef on " + this.getClass().getSimpleName());
            }
        }
    }

    /**
     * Retrieves the specific error code identifier.
     *
     * @return The string representing the error code.
     */
    public String getErrorCode() { return errorCode; }

    /**
     * Retrieves the standardized exception name.
     *
     * @return The string representing the exception name.
     */
    public String getExceptionName() { return exceptionName; }

    /**
     * Retrieves the descriptive error message.
     *
     * @return The error message string.
     */
    @Override public String getMessage() { return message; }

    /**
     * Retrieves the HTTP status code.
     *
     * @return The integer HTTP status code.
     */
    public int getStatus() { return status; }

    /**
     * Retrieves any detailed structural errors or validation messages.
     *
     * @return A map of error fields and messages, or null if none exist.
     */
    public Map<String, String> getErrors() { return errors; }

    /**
     * Retrieves the precise timestamp of when the exception occurred.
     *
     * @return The timestamp as a {@link LocalDateTime}.
     */
    public LocalDateTime getTimestamp() { return timestamp; }

    /**
     * Builder class for dynamically constructing instances of {@link BaseException} subclasses
     * by overriding properties mapped in the default catalog.
     *
     * @param <E> The specific subtype of BaseException being built.
     */
    public static class Builder<E extends BaseException> {
        private final Class<E> clazz;
        private String errorCode;
        private String exceptionName;
        private String message;
        private int status;
        private Map<String, String> errors;

        /**
         * Initializes a builder for the specified exception class and preloads 
         * default catalog values.
         *
         * @param clazz The runtime class of the exception to build.
         * @throws IllegalArgumentException If the class is missing the {@link ExceptionDef} annotation.
         */
        public Builder(Class<E> clazz) {
            this.clazz = clazz;
            ExceptionDef def = clazz.getAnnotation(ExceptionDef.class);
            if (def == null) throw new IllegalArgumentException("Missing @ExceptionDef on " + clazz.getSimpleName());

            BaseExceptionCatalog catalog = findCatalog(clazz, def);
            this.errorCode = catalog.getErrorCode();
            this.exceptionName = catalog.getExceptionName();
            this.message = catalog.getMessage();
            this.status = catalog.getStatus();
            this.errors = catalog.getErrors();
        }

        /**
         * Overrides the default error code.
         *
         * @param errorCode The new error code.
         * @return The current builder instance.
         */
        public Builder<E> withErrorCode(String errorCode) {
            this.errorCode = errorCode; return this;
        }

        /**
         * Overrides the default exception name.
         *
         * @param exceptionName The new exception name.
         * @return The current builder instance.
         */
        public Builder<E> withExceptionName(String exceptionName) {
            this.exceptionName = exceptionName; return this;
        }

        /**
         * Overrides the default message.
         *
         * @param message The new error message.
         * @return The current builder instance.
         */
        public Builder<E> withMessage(String message) {
            this.message = message; return this;
        }

        /**
         * Overrides the default HTTP status code.
         *
         * @param status The new HTTP status code.
         * @return The current builder instance.
         */
        public Builder<E> withStatus(int status) {
            this.status = status; return this;
        }

        /**
         * Injects or overrides custom validation errors.
         *
         * @param errors A map of field-level errors.
         * @return The current builder instance.
         */
        public Builder<E> withErrors(Map<String, String> errors) {
            this.errors = errors; return this;
        }

        /**
         * Finalizes the build process and instantiates the specific exception class.
         *
         * @return A newly constructed instance of type {@code E}.
         * @throws RuntimeException If reflection fails to instantiate the class.
         */
        public E build() {
            try {
                CURRENT_BUILDER.set(this);
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error creating " + clazz.getSimpleName(), e);
            } finally {
                CURRENT_BUILDER.remove();
            }
        }
    }

    /**
     * Resolves the catalog entry for a given exception class dynamically by looking
     * for an enum constant that matches the SNAKE_CASE version of the exception's class name.
     *
     * @param exceptionClass The runtime class of the exception.
     * @param def            The annotation definition mapping to the enum catalog.
     * @return The resolved {@link BaseExceptionCatalog} implementation.
     * @throws IllegalStateException If the naming convention is broken or the constant is missing.
     */
    private static BaseExceptionCatalog findCatalog(Class<?> exceptionClass, ExceptionDef def) {
        Class<? extends BaseExceptionCatalog> enumClass = def.value();

        String className = exceptionClass.getSimpleName();
        String baseName = className.endsWith("Exception")
                ? className.substring(0, className.length() - 9)
                : className;
        String expectedEnumName = baseName.replaceAll("([a-z])([A-Z]+)", "$1_$2").toUpperCase();

        Object[] constants = enumClass.getEnumConstants();
        if (constants != null) {
            for (Object enumConstant : constants) {
                if (((Enum<?>) enumConstant).name().equals(expectedEnumName)) {
                    return (BaseExceptionCatalog) enumConstant;
                }
            }
        }

        throw new IllegalStateException("Broken convention: Constant '" + expectedEnumName +
                "' not found in catalog " + enumClass.getSimpleName() + " for class " + className);
    }
}
