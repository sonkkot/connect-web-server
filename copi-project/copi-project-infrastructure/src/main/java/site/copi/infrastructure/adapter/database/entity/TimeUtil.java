package site.copi.infrastructure.adapter.database.entity;

import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class TimeUtil {
    private static final String ASIA_SEOUL = "Asia/Seoul";

    public static Instant convert(final ZonedDateTime target) {
        return target.toInstant();
    }

    public static ZonedDateTime convert(final Instant target) {
        return target.atZone(ZoneId.of(ASIA_SEOUL));
    }
}