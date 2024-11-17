package site.copi.users.domain.value;

import jakarta.validation.constraints.NotBlank;
import site.copi.common.validator.ValueObjectValidator;

public record UserProfileImage(
    @NotBlank
    String path
) {

    public UserProfileImage(String path) {
        this.path = path;
        ValueObjectValidator.validate(this);
    }
}
