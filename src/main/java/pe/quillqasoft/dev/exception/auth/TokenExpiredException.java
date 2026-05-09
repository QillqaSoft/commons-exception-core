package pe.quillqasoft.dev.exception.auth;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.auth.AuthErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when the authentication token provided by the client has expired.
 */
@ExceptionDef(AuthErrorCatalog.class)
public class TokenExpiredException extends BaseException {

    /**
     * Constructs a new TokenExpiredException resolving its default properties from {@link AuthErrorCatalog}.
     */
    public TokenExpiredException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link TokenExpiredException}.
     *
     * @return A builder instance.
     */
    public static Builder<TokenExpiredException> builder() {
        return new Builder<>(TokenExpiredException.class);
    }
}
