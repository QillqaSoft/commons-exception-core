package pe.quillqasoft.dev.exception.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseExceptionsTest {

    @Test
    void testGenericDatabaseException() {
        GenericDatabaseException exception = new GenericDatabaseException();

        assertEquals("ERR-DB-001", exception.getErrorCode());
        assertEquals("DATA_ACCESS_ERROR", exception.getExceptionName());
        assertEquals("A data access error occurred. The database operation could not be completed.", exception.getMessage());
        assertEquals(500, exception.getStatus());
        assertNull(exception.getErrors());
    }
}
