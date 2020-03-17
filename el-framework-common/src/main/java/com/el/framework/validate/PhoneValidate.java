package com.el.framework.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author mrd
 * @description 手机号校验
 * @date 2020/03/14 11:24
 */
public class PhoneValidate implements ConstraintValidator<Phone,String> {
    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String pattern = "1[3|4|5|6|7|8|9][0-9]\\d{8}";
        return Pattern.matches(pattern, value);
    }
}
