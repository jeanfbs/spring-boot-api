package br.com.sampleapi.service.mapper;

import br.com.sampleapi.db.entity.PersonEntity;
import br.com.sampleapi.dto.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    PersonEntity toEntity(Person person);

    Person toDto(PersonEntity entity);

}

