package site.copi.users.domain.value;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import site.copi.common.validator.ValueObjectValidator;

public record UserRepository(
    @NotBlank
    String name,
    @NotBlank
    String path,
    @NotBlank
    String image
) {

    @Builder
    public UserRepository(
        String name,
        String path,
        String image
    ) {
        this.name = name;
        this.path = path;
        this.image = image;
        ValueObjectValidator.validate(this);
    }
}