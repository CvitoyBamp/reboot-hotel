package ru.reboot.hotel.utils.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidLogin.ValidLoginValidator.class })
public @interface ValidLogin {
    String message() default "message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidLoginValidator implements ConstraintValidator<ValidLogin, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            String message = "";
            if (value == null) {
                message = "Имя не может быть пустым.";
            }
            else if (message.length() < 3 || message.length() > 128) {
                message = "Имя должно быть длиной от 3 до 128 символов.";
            }
            if (!message.isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            }
            return false;
        }
    }
}
