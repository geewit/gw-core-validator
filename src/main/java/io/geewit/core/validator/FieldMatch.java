package io.geewit.core.validator;

import io.geewit.core.validator.impl.FieldMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 2个值比较是否相等
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
 * {@code @FieldMatch.List({
 *  * @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
 *  * @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")})
 * }
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
@SuppressWarnings({"unchecked", "unused"})
public @interface FieldMatch {
    String message() default "{javax.validation.constraints.FieldMatch.message}";

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    /**
     * 允许为null
     * @return
     */
    boolean allowNull() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     *
     * @see FieldMatch
     */
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}
