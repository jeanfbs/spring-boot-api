package br.com.sampleapi.dto;

import jakarta.validation.Valid;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true, builderClassName = "Builder")
public record FieldsErrors(
        List<@Valid FieldErrorItem> errors
) {
}
