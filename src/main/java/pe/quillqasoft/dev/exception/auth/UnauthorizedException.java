package pe.quillqasoft.dev.exception.auth;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.auth.AuthErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when authentication is required but missing or invalid for a specific resource.
 */
@ExceptionDef(AuthErrorCatalog.class)
public class UnauthorizedException extends BaseException {

    /**
     * Constructs a new UnauthorizedException resolving its default properties from {@link AuthErrorCatalog}.
     */
    public UnauthorizedException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link UnauthorizedException}.
     *
     * @return A builder instance.
     */
    public static Builder<UnauthorizedException> builder() {
        return new Builder<>(UnauthorizedException.class);
    }
}
