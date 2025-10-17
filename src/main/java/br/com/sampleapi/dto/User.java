package br.com.sampleapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true, builderClassName = "Builder")
public record User(

        @Null UUID id,
        @NotBlank String name,

        List<String> emails,

        @NotBlank String password,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate
) {


    public String emailsToString(){
        return !CollectionUtils.isEmpty(emails) ?  String.join(",", emails) : null;
    }
}
