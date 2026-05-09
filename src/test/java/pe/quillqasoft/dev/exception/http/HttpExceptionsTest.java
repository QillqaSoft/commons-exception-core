package pe.quillqasoft.dev.exception.http;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpExceptionsTest {

    @Test
    void testHttpException() {
        HttpException exception = new HttpException();

        assertEquals("ERR-HTTP-500", exception.getErrorCode());
        assertEquals("GENERIC_HTTP_ERROR", exception.getExceptionName());
        assertEquals("An unexpected HTTP error occurred while processing the request.", exception.getMessage());
        assertEquals(500, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testValidException() {
        ValidException exception = new ValidException();

        assertEquals("ERR-HTTP-400", exception.getErrorCode());
        assertEquals("VALIDATION_ERROR", exception.getExceptionName());
        assertEquals("The request contains invalid data. Please verify your input.", exception.getMessage());
        assertEquals(400, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testValidException_WithCustomErrors() {
        Map<String, String> errors = Map.of("email", "Must be a valid email address");
        ValidException exception = ValidException.builder()
                .withErrors(errors)
                .build();

        assertEquals("ERR-HTTP-400", exception.getErrorCode());
        assertEquals("VALIDATION_ERROR", exception.getExceptionName());
        assertEquals("The request contains invalid data. Please verify your input.", exception.getMessage());
        assertEquals(400, exception.getStatus());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testNotFoundException() {
        NotFoundException exception = new NotFoundException();

        assertEquals("ERR-HTTP-404", exception.getErrorCode());
        assertEquals("RESOURCE_NOT_FOUND", exception.getExceptionName());
        assertEquals("The requested resource could not be found.", exception.getMessage());
        assertEquals(404, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testWebClientException() {
        WebClientException exception = new WebClientException();

        assertEquals("ERR-HTTP-502", exception.getErrorCode());
        assertEquals("EXTERNAL_SERVICE_COMMUNICATION_ERROR", exception.getExceptionName());
        assertEquals("Error communicating with the external service. Please try again later.", exception.getMessage());
        assertEquals(502, exception.getStatus());
        assertNull(exception.getErrors());
    }
}
