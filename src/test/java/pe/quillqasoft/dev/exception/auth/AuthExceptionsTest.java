package pe.quillqasoft.dev.exception.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthExceptionsTest {

    @Test
    void testForbiddenException() {
        ForbiddenException exception = new ForbiddenException();

        assertEquals("ERR-AUTH-403", exception.getErrorCode());
        assertEquals("FORBIDDEN", exception.getExceptionName());
        assertEquals("Access is denied. You do not have permission to access this resource.", exception.getMessage());
        assertEquals(403, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testTokenExpiredException() {
        TokenExpiredException exception = new TokenExpiredException();

        assertEquals("ERR-AUTH-001", exception.getErrorCode());
        assertEquals("TOKEN_EXPIRED", exception.getExceptionName());
        assertEquals("The provided authentication token has expired. Please log in again.", exception.getMessage());
        assertEquals(401, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testUnauthorizedException() {
        UnauthorizedException exception = new UnauthorizedException();

        assertEquals("ERR-AUTH-401", exception.getErrorCode());
        assertEquals("UNAUTHORIZED", exception.getExceptionName());
        assertEquals("Authentication is required to access this resource.", exception.getMessage());
        assertEquals(401, exception.getStatus());
        assertNull(exception.getErrors());
    }

    @Test
    void testInvalidCredentialsException() {
        InvalidCredentialsException exception = new InvalidCredentialsException();

        assertEquals("ERR-AUTH-002", exception.getErrorCode());
        assertEquals("INVALID_CREDENTIALS", exception.getExceptionName());
        assertEquals("The provided credentials are incorrect.", exception.getMessage());
        assertEquals(401, exception.getStatus());
        assertNull(exception.getErrors());
    }
}
