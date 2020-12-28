package io.geewit.core.validator;

import io.geewit.core.validator.impl.AnyNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 判断值不能全为空
 *
 * @author geewit
 * @since 2015-05-18
 *
 * Example, compare more than 1 pair of fields:
 *
 * Validation annotation to validate that 2 fields have the same value.
 * An array of fields and their matching confirmation fields can be supplied.
 * <p>
 * Example, compare 1 pair of fields:
 * {@code @AnyNotEmpty(parameters = {"field1", "field2", message = "The field1 and field2 must not all empty")})
 * }
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = AnyNotEmptyValidator.class)
@Documented
@SuppressWarnings({"unchecked", "unused"})
public @interface AnyNotEmpty {
    String message() default "{javax.validation.constraints.AnyNotEmpty.message}";

    /**
     * @return The first field
     */
    String[] parameters() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     *
     * @see AnyNotEmpty
     */
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        AnyNotEmpty[] value();
    }
}
