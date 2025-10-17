package br.com.sampleapi.service.mapper;

import br.com.sampleapi.db.entity.CategoryEntity;
import br.com.sampleapi.dto.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryEntity toEntity(Category entity);

    Category toDto(CategoryEntity entity);

}

