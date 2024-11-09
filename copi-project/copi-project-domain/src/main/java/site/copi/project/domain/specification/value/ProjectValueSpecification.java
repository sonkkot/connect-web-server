package site.copi.project.domain.specification.value;

import java.util.Objects;
import java.util.function.Supplier;

public final class ProjectValueSpecification {

    public static String requiredString(final String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Invalid.. NotEmpty");
        }

        return value;
    }

    public static Long requiredPositive(final Long value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Invalid.. Positive");
        }

        return value;
    }

    public static Integer requiredPositive(final Integer value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Invalid.. Positive");
        }

        return value;
    }

    public static <T> T ifNullAction(final T value, final Supplier<T> action) {
        if (value == null) {
            return action.get();
        }

        return value;
    }

    public static <T> T requiredNotNull(final T t) {
        if (t == null) {
            throw new IllegalArgumentException("Invalid.. NotNull");
        }

        return t;
    }

    public static boolean isEquals(final Object v1, final Object v2) {
        return Objects.equals(v1, v2);
    }

    public static boolean isNotEquals(final Object v1, final Object v2) {
        return !Objects.equals(v1, v2);
    }
}