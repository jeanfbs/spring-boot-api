package br.com.sampleapi.service.mapper;

import br.com.sampleapi.db.entity.UserEntity;
import br.com.sampleapi.dto.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "emails", target = "emails", qualifiedByName = "listToEmailString")
    UserEntity toEntity(User user);

    @Mapping(source = "emails", target = "emails", qualifiedByName = "emailStringToList")
    User toDto(UserEntity entity);


    @Named("emailStringToList")
    default List<String> emailStringToList(String emails) {
        if (emails == null || emails.isBlank()) {
            return List.of();
        }
        return List.of(emails.split(","));
    }

    @Named("listToEmailString")
    default String listToEmailString(List<String> emails) {
        if (emails == null || emails.isEmpty()) {
            return "";
        }
        return String.join(",", emails);
    }

}

