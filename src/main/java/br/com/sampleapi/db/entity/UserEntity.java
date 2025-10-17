package br.com.sampleapi.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true, builderClassName = "Builder")
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity{

        @Id
        @GeneratedValue
        @UuidGenerator
        private UUID id;

        @Column(nullable = false)
        private String name;

        @Column
        private String emails;

        @Column(nullable = false)
        private String password;

        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private LocalDate birthDate;
}
