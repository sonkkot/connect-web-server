package site.copi.common.validator;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ValueObjectValidator {
    private static final Validator VALIDATOR;

    static {
        VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();
    }

    public static <T> void validate(final T target) {
        final var result = VALIDATOR.validate(target);

        if (!result.isEmpty()) {
            final var message = new StringBuilder();

            result.forEach(v -> message
                .append(System.lineSeparator())
                .append(" - ")
                .append(v.getPropertyPath().toString())
                .append(" : ")
                .append(v.getMessage())
            );

            log.info("[   ValueObjectValidator   ] 🟠 Class-path: {} \n Invalid: {}", target.getClass(), message);
            // TODO : throw new..
        }
    }
}