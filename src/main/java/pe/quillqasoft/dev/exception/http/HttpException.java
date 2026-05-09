package pe.quillqasoft.dev.exception.http;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.http.HttpErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when a generic HTTP error occurs during the processing of a request.
 */
@ExceptionDef(HttpErrorCatalog.class)
public class HttpException extends BaseException {

    /**
     * Constructs a new HttpException resolving its default properties from {@link HttpErrorCatalog}.
     */
    public HttpException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link HttpException}.
     *
     * @return A builder instance.
     */
    public static Builder<HttpException> builder() {
        return new Builder<>(HttpException.class);
    }
}
