package br.com.sampleapi.dto;

import br.com.sampleapi.db.entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record Category (
        UUID categoryId,
        @NotBlank String name,
        List<ProductEntity> products,
        Boolean status
) {
}
