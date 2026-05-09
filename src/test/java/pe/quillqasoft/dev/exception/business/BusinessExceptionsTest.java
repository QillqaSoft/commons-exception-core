package pe.quillqasoft.dev.exception.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionsTest {

    @Test
    void testGenericBusinessException() {
        GenericBusinessException exception = new GenericBusinessException();

        assertEquals("ERR-BIZ-400", exception.getErrorCode());
        assertEquals("BUSINESS_RULE_VIOLATION", exception.getExceptionName());
        assertEquals("A business rule violation occurred. Please verify your request.", exception.getMessage());
        assertEquals(400, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testGenericBusinessException_WithBuilder() {
        GenericBusinessException exception = GenericBusinessException.builder()
                .withMessage("Custom business error")
                .build();

        assertEquals("ERR-BIZ-400", exception.getErrorCode());
        assertEquals("BUSINESS_RULE_VIOLATION", exception.getExceptionName());
        assertEquals("Custom business error", exception.getMessage());
        assertEquals(400, exception.getStatus());
        assertNull(exception.getErrors());
    }
}
