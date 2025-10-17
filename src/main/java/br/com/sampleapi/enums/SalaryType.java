package br.com.sampleapi.enums;

import br.com.sampleapi.exception.UnprocessableEntityException;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;

@Getter
public enum SalaryType {

    DAYS("min", SalaryType::calculateSalaryMinimum),
    MONTHS("full", SalaryType::calculateSalary);

    private final String name;
    private final Function<Long, String> converter;
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.of("pt", "BR"));
    static{
        NUMBER_FORMAT.setMaximumFractionDigits(2);
        NUMBER_FORMAT.setMinimumFractionDigits(2);
    }

    SalaryType(String name, Function<Long, String> converter) {
        this.name = name;
        this.converter = converter;
    }

    public static SalaryType fromName(final String name) {
        return Arrays.stream(values()).filter(e -> e.name.equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new UnprocessableEntityException("Salary type is invalid"));
    }


    private static String calculateSalaryMinimum(long years) {
        double salaryMin = 1302.0;
        var salary = getSalary(years);
        var result = new BigDecimal(salary).divide(new BigDecimal(salaryMin), 2, RoundingMode.HALF_UP).floatValue();
        return "%.2f".formatted(result);
    }

    private static String calculateSalary(long years) {
        double salary = getSalary(years);
        return NUMBER_FORMAT.format(salary);
    }

    private static double getSalary(long years) {
        double salary = 1558.0;
        for (int i = 0; i < years; i++) {
            salary += (salary * 0.18) + 500;
        }
        return new BigDecimal(salary)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
