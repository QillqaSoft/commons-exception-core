package pe.quillqasoft.dev.exception.generic;

import pe.quillqasoft.dev.core.annotation.ExceptionDef;
import pe.quillqasoft.dev.core.catalog.generic.GenericRuntimeErrorCatalog;
import pe.quillqasoft.dev.core.model.BaseException;

/**
 * Exception thrown when an unexpected or uncategorized runtime error occurs.
 */
@ExceptionDef(GenericRuntimeErrorCatalog.class)
public class GenericRuntimeException extends BaseException {

    /**
     * Constructs a new GenericRuntimeException resolving its default properties from {@link GenericRuntimeErrorCatalog}.
     */
    public GenericRuntimeException() {
        super();
    }

    /**
     * Creates a builder to construct a customized {@link GenericRuntimeException}.
     *
     * @return A builder instance.
     */
    public static Builder<GenericRuntimeException> builder() {
        return new Builder<>(GenericRuntimeException.class);
    }
}
