package pe.quillqasoft.dev.exception.http;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.http.HttpErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when a requested resource could not be found in the system.
 */
@ExceptionDef(HttpErrorCatalog.class)
public class NotFoundException extends BaseException {

    /**
     * Constructs a new NotFoundException resolving its default properties from {@link HttpErrorCatalog}.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link NotFoundException}.
     *
     * @return A builder instance.
     */
    public static Builder<NotFoundException> builder() {
        return new Builder<>(NotFoundException.class);
    }
}
