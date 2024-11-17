package site.copi.users.domain.value;

import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import site.copi.common.validator.ValueObjectValidator;

public record UserId(
    @NonNull
    @Positive
    Long id
) {

    public UserId(Long id) {
        this.id = id;
        ValueObjectValidator.validate(this);
    }
}