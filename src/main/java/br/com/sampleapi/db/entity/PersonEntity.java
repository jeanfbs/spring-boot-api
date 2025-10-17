package br.com.sampleapi.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Builder(toBuilder = true, builderClassName = "Builder")
@Data
@Entity
@Table(name = "persons")
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate admissionAt;
}
