package io.geewit.core.validator.impl;

import io.geewit.core.validator.FieldMatch;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 判断2个值比较是否相等
 *
 * @author geewit
 * @since 2015-05-18
 */
@SuppressWarnings({"unused"})
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private transient final static Logger logger = LoggerFactory.getLogger(FieldMatchValidator.class);

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            if (firstObj == null && secondObj == null) {
                return true;
            }
            logger.debug("firstObj = " + firstObj);
            logger.debug("secondObj = " + secondObj);
            return firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception e) {
            logger.warn(e.getMessage());
        }
        return true;
    }
}
