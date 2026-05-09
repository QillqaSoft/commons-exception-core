package pe.quillqasoft.dev.exception.auth;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.auth.AuthErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when a user is authenticated but does not have the required 
 * permissions to access a specific resource.
 */
@ExceptionDef(AuthErrorCatalog.class)
public class ForbiddenException extends BaseException {

    /**
     * Constructs a new ForbiddenException resolving its default properties from {@link AuthErrorCatalog}.
     */
    public ForbiddenException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link ForbiddenException}.
     *
     * @return A builder instance.
     */
    public static Builder<ForbiddenException> builder() {
        return new Builder<>(ForbiddenException.class);
    }
}
