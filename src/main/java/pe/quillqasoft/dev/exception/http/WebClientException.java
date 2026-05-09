package pe.quillqasoft.dev.exception.http;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.http.HttpErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when an error occurs during communication with an external web service or API.
 */
@ExceptionDef(HttpErrorCatalog.class)
public class WebClientException extends BaseException {

    /**
     * Constructs a new WebClientException resolving its default properties from {@link HttpErrorCatalog}.
     */
    public WebClientException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link WebClientException}.
     *
     * @return A builder instance.
     */
    public static Builder<WebClientException> builder() {
        return new Builder<>(WebClientException.class);
    }
}
