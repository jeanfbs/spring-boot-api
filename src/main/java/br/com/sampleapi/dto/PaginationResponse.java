package br.com.sampleapi.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true, builderClassName = "Builder")
public record PaginationResponse<T>(
        List<T> content,
        Long totalFiltered,
        Long totalPages,
        Boolean first,
        Boolean last
){ }
