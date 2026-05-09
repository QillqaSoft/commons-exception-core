package pe.quillqasoft.dev.exception.business;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.business.BusinessErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when a general business rule is violated.
 */
@ExceptionDef(BusinessErrorCatalog.class)
public class GenericBusinessException extends BaseException {

    /**
     * Constructs a new GenericBusinessException resolving its default properties from {@link BusinessErrorCatalog}.
     */
    public GenericBusinessException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link GenericBusinessException}.
     *
     * @return A builder instance.
     */
    public static Builder<GenericBusinessException> builder() {
        return new Builder<>(GenericBusinessException.class);
    }
}
