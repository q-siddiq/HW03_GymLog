package com.example.hw03_gymlog.database.typeConverters;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * Converts LocalDateTime values to and from long values for Room database storage.
 */
public class LocalDateTypeConverter {
    /**
     * Converts a LocalDateTime into a long value (milliseconds since epoch)
     * so it can be stored in the Room database.
     *
     * @param date the LocalDateTime to convert
     * @return milliseconds since epoch
     */
    @TypeConverter
    public long convertDateToLong(LocalDateTime date) {
        ZonedDateTime zdt = ZonedDateTime.of(date, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    /**
     * Converts a long value (milliseconds since epoch) back into a LocalDateTime
     * when reading from the Room database.
     *
     * @param epochMilli stored time in milliseconds
     * @return the LocalDateTime representation
     */
    @TypeConverter
    public LocalDateTime convertLongToDate(Long epochMilli) {
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
