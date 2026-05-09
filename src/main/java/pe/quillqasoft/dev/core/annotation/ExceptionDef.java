package pe.quillqasoft.dev.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pe.quillqasoft.dev.core.catalog.BaseExceptionCatalog;

/**
 * Annotation used to bind a specific exception class to its corresponding error catalog.
 * <p>
 * Classes extending {@link pe.quillqasoft.dev.core.model.BaseException} must be annotated with this
 * to allow automatic property resolution via reflection.
 * </p>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionDef {
    /**
     * Specifies the catalog class containing the mapped exception definition.
     *
     * @return The class type of the catalog, which must implement {@link BaseExceptionCatalog}.
     */
    Class<? extends BaseExceptionCatalog> value();
}
