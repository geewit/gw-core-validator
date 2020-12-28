package io.geewit.core.validator.impl;

import io.geewit.core.validator.AnyNotEmpty;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 判断值不能全为空
 *
 * @author geewit
 * @since 2015-05-18
 */
@SuppressWarnings({"unused"})
public class AnyNotEmptyValidator implements ConstraintValidator<AnyNotEmpty, Object> {
    private transient final static Logger logger = LoggerFactory.getLogger(AnyNotEmptyValidator.class);

    private String[] parameters;

    @Override
    public void initialize(final AnyNotEmpty constraintAnnotation) {
        parameters = constraintAnnotation.parameters();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            for(String parameter : this.parameters) {
                final Object parameterObj = BeanUtils.getProperty(value, parameter);
                if(parameterObj != null) {
                    if(parameterObj.toString().length() > 0) {
                        return true;
                    }
                }
            }

            return false;
        } catch (final Exception e) {
            logger.warn(e.getMessage());
        }
        return true;
    }
}
