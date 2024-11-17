package site.copi.users.domain.value;

import jakarta.validation.constraints.NotBlank;
import site.copi.common.validator.ValueObjectValidator;
public record UserNickname(
    @NotBlank
    String nickname
) {

    public UserNickname(String nickname) {
        this.nickname = nickname;
        ValueObjectValidator.validate(this);
    }
}