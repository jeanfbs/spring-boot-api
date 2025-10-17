package br.com.sampleapi.dto;

import lombok.Builder;

@Builder(toBuilder = true, builderClassName = "Builder")
public record FieldErrorItem(

        String field,
        String message
) {
}
