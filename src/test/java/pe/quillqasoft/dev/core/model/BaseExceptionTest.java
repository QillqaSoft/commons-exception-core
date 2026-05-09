package pe.quillqasoft.dev.core.model;

import org.junit.jupiter.api.Test;
import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BaseExceptionTest {

    public enum DummyCatalog implements BaseExceptionCatalog {
        DUMMY(
                "ERR-TEST-001",
                "DUMMY_ERROR",
                "This is a dummy error message",
                400,
                null
        ),
        DUMMY_WITH_ERRORS(
                "ERR-TEST-002",
                "DUMMY_ERROR_WITH_ERRORS",
                "Error with details",
                422,
                Map.of("field", "Invalid value")
        );

        private final String errorCode;
        private final String exceptionName;
        private final String message;
        private final int status;
        private final Map<String, String> errors;

        DummyCatalog(String errorCode, String exceptionName, String message, int status, Map<String, String> errors) {
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

    @ExceptionDef(DummyCatalog.class)
    public static class DummyException extends BaseException {
        public static Builder<DummyException> builder() {
            return new Builder<>(DummyException.class);
        }
    }

    @ExceptionDef(DummyCatalog.class)
    public static class NonMatchingConventionException extends BaseException {
    }

    public static class MissingAnnotationException extends BaseException {
    }


    @Test
    void testExceptionCreation_WithDefaultConstructor_AutomaticallyResolvesProperties() {
        DummyException exception = new DummyException();

        assertNotNull(exception.getTimestamp());
        assertEquals("ERR-TEST-001", exception.getErrorCode());
        assertEquals("DUMMY_ERROR", exception.getExceptionName());
        assertEquals("This is a dummy error message", exception.getMessage());
        assertEquals(400, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testExceptionCreation_WithBuilder_OverridesDefaultProperties() {
        Map<String, String> customErrors = Map.of("username", "Must not be empty");

        DummyException exception = DummyException.builder()
                .withMessage("Overridden message")
                .withErrorCode("ERR-CUSTOM")
                .withExceptionName("CUSTOM_EXCEPTION_NAME")
                .withStatus(404)
                .withErrors(customErrors)
                .build();

        assertNotNull(exception.getTimestamp());
        assertEquals("ERR-CUSTOM", exception.getErrorCode());
        assertEquals("CUSTOM_EXCEPTION_NAME", exception.getExceptionName());
        assertEquals("Overridden message", exception.getMessage());
        assertEquals(404, exception.getStatus());
        assertEquals(customErrors, exception.getErrors());
    }

    @Test
    void testExceptionCreation_WithBuilder_UsesDefaultPropertiesWhenNotOverridden() {
        DummyException exception = DummyException.builder()
                .withMessage("Only overriding message")
                .build();

        assertEquals("ERR-TEST-001", exception.getErrorCode());
        assertEquals("DUMMY_ERROR", exception.getExceptionName());
        assertEquals("Only overriding message", exception.getMessage());
        assertEquals(400, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testExceptionCreation_ThrowsIllegalStateException_IfConventionIsBroken() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, NonMatchingConventionException::new);
        assertTrue(exception.getMessage().contains("Broken convention: Constant 'NON_MATCHING_CONVENTION'"));
    }

    @Test
    void testExceptionCreation_ThrowsIllegalStateException_IfAnnotationIsMissing() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, MissingAnnotationException::new);
        assertEquals("Missing @ExceptionDef on MissingAnnotationException", exception.getMessage());
    }

    @Test
    void testBuilderCreation_ThrowsIllegalArgumentException_IfAnnotationIsMissing() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new BaseException.Builder<>(MissingAnnotationException.class);
        });
        assertEquals("Missing @ExceptionDef on MissingAnnotationException", exception.getMessage());
    }
}
