package br.com.sampleapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true, builderClassName = "Builder")
public record Person(

        Long id,
        @NotBlank String name,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate admissionAt
) {
}
