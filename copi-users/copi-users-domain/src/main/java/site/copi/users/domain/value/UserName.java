package site.copi.users.domain.value;

import jakarta.validation.constraints.NotBlank;
import site.copi.common.validator.ValueObjectValidator;

public record UserName(
    @NotBlank
    String name
) {

    public UserName(String name) {
        this.name = name;
        ValueObjectValidator.validate(this);
    }
}