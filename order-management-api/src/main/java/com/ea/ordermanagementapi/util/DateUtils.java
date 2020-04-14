package com.ea.ordermanagementapi.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.ea.ordermanagementapi.constant.DefaultConstants;


public final class DateUtils
{
    private static final DateTimeFormatter tlDateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm",
            DefaultConstants.DEFAULT_LOCALE);

    private static final DateTimeFormatter tlDateTimeFormat = DateTimeFormatter.ofPattern("HH:mm",
            DefaultConstants.DEFAULT_LOCALE);

    // ----

    public static String getNowDate()
    {
        LocalDateTime time = getNowAsLocalDateTime();
        return time.format(tlDateFormat);
    }

    public static LocalDateTime getNowAsLocalDateTime()
    {
        return LocalDateTime.of(LocalDate.now(), LocalTime.now());
    }

    public static long getNow()
    {
        return Instant.now().getEpochSecond();
    }

    public static boolean isAfterNow(String date)
    {
        return tlDateFormat.parse(date, LocalDateTime::from)
                .isAfter(getNowAsLocalDateTime());
    }

    public static long asEpocSecond(String date)
    {
        return LocalDateTime.from(tlDateFormat.parse(date)).toEpochSecond(ZoneOffset.UTC);
    }
}