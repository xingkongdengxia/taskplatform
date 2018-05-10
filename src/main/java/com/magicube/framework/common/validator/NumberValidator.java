package com.magicube.framework.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 是否为数字
 *
 * @author justincai
 */
public class NumberValidator extends ValidatorHandler<String> implements Validator<String> {

    private final String fieldName;

    public NumberValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (!NumberUtils.isDigits(s)) {
            context.addError(ValidationError.create(String.format("%s不是数字！", fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(s));
            return false;
        }
        return true;
    }

}
