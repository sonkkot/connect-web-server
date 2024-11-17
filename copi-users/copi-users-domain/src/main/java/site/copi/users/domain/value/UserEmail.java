package site.copi.users.domain.value;

import jakarta.validation.constraints.NotBlank;
import site.copi.common.validator.ValueObjectValidator;

public record UserEmail(
    @NotBlank
    String email
) {

    public UserEmail(String email) {
        this.email = email;
        ValueObjectValidator.validate(this);
    }
}