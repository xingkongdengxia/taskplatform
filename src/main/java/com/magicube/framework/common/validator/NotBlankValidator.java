package com.magicube.framework.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 校验不为null且不为空字符串且不为空格
 *
 * @author justincai
 */
public class NotBlankValidator extends ValidatorHandler<String> implements Validator<String> {

    private final String fieldName;

    public NotBlankValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (StringUtils.isBlank(s)) {
            context.addError(ValidationError.create(String.format("%s不能为空或空字符串或空格！", fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(s));
            return false;
        }
        return true;
    }

}
