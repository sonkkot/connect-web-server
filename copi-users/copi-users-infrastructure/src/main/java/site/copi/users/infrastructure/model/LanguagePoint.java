package site.copi.users.infrastructure.model;

public record LanguagePoint(
    String name,
    Long point
) {

    public static LanguagePoint of(String name, Long point) {
        return new LanguagePoint(name, point);
    }
}