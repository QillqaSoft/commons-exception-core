package pe.quillqasoft.dev.exception.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericRuntimeExceptionTest {

    @Test
    void testGenericRuntimeException_DefaultCreation() {
        GenericRuntimeException exception = new GenericRuntimeException();

        assertEquals("ERR-SYS-500", exception.getErrorCode());
        assertEquals("INTERNAL_SERVER_ERROR", exception.getExceptionName());
        assertEquals("An unexpected internal server error occurred. Please try again later.", exception.getMessage());
        assertEquals(500, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testGenericRuntimeException_BuilderCreation() {
        GenericRuntimeException exception = GenericRuntimeException.builder()
                .withMessage("Custom internal error")
                .build();

        assertEquals("ERR-SYS-500", exception.getErrorCode());
        assertEquals("INTERNAL_SERVER_ERROR", exception.getExceptionName());
        assertEquals("Custom internal error", exception.getMessage());
        assertEquals(500, exception.getStatus());
        assertNull(exception.getErrors());
    }
}
