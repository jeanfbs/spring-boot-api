package br.com.sampleapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder(toBuilder = true, builderClassName = "Builder")
public record Error(
        @NotNull Integer code,
        @NotNull String message
) {
}
