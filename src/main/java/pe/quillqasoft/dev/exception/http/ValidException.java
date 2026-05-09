package pe.quillqasoft.dev.exception.http;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.http.HttpErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when a request payload fails validation rules.
 */
@ExceptionDef(HttpErrorCatalog.class)
public class ValidException extends BaseException {

    /**
     * Constructs a new ValidException resolving its default properties from {@link HttpErrorCatalog}.
     */
    public ValidException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link ValidException}.
     *
     * @return A builder instance.
     */
    public static Builder<ValidException> builder() {
        return new Builder<>(ValidException.class);
    }
}
