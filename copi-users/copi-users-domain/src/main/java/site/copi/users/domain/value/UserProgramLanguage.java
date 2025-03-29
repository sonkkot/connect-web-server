package site.copi.users.domain.value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.NonNull;
import site.copi.common.validator.ValueObjectValidator;

public record UserProgramLanguage(
    @NotBlank
    String language,
    @NonNull
    @PositiveOrZero
    Long point,
    @NotBlank
    String image
) {

    @Builder
    public UserProgramLanguage(
        String language,
        Long point,
        String image
    ) {
        this.language = language;
        this.point = point;
        this.image = image;
        ValueObjectValidator.validate(this);
    }
}