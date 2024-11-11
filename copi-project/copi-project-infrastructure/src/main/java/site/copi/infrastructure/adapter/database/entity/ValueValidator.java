package site.copi.infrastructure.adapter.database.entity;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueValidator {

    private static final Validator validator;

    static {
        validator = Validation
            .buildDefaultValidatorFactory()
            .getValidator();
    }

    public static <T> void validate(final T vo) {
        final var violations = validator.validate(vo);

        if (!violations.isEmpty()) {
            final var message = new StringBuilder();

            violations.forEach(v -> createMessage(message, v));

            log.info("[   ValueValidator.validate   ] 🟠 Class-Path: {} \n Invalid fields: {}", vo.getClass(), message);
            throw new IllegalArgumentException("Invalid.. Value");
        }
    }

    private static <T> StringBuilder createMessage(StringBuilder message, ConstraintViolation<T> v) {
        return message
            .append(System.lineSeparator())
            .append(" - ")
            .append(v.getPropertyPath().toString())
            .append(" : ")
            .append(v.getMessage());
    }

    public static String requiredHeader(final String vo) {
        if (vo == null || vo.isBlank()) {
            throw new IllegalArgumentException("Invalid.. Header");
        }

        return vo;
    }
}
