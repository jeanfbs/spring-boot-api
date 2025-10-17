package br.com.sampleapi.enums;

import br.com.sampleapi.exception.UnprocessableEntityException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.function.Function;

@Getter
public enum AgeType {

    DAYS("days", AgeType::sinceInDays),
    MONTHS("months", AgeType::sinceInMonths),
    YEARS("years", AgeType::sinceInYear);

    private final String name;


    private final Function<LocalDate, Long> converter;

    AgeType(String name, Function<LocalDate, Long> converter) {
        this.name = name;
        this.converter = converter;
    }

    public static AgeType fromName(final String name) {
        return Arrays.stream(values()).filter(e -> e.name.equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new UnprocessableEntityException("Age type is invalid"));
    }


    private static long sinceInDays(LocalDate date) {
        var period =  Period.between(date, LocalDate.now());
        return period.getDays() + period.getMonths() * 30L + period.getYears() * 365L;
    }

    private static long sinceInMonths(LocalDate date) {
        return Period.between(date, LocalDate.now()).toTotalMonths();
    }

    private static long sinceInYear(LocalDate date) {
//        var months = Period.between(date, LocalDate.now()).toTotalMonths();
        var months = Period.between(date, LocalDate.of(2023, 2, 7)).toTotalMonths();
        return months / 12;
    }


}
