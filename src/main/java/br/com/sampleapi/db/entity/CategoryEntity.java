package br.com.sampleapi.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true, builderClassName = "Builder")
@Data
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

        @Id
        @GeneratedValue
        @UuidGenerator
        private UUID categoryId;

        @Column(nullable = false)
        private String name;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
        private List<ProductEntity> products;

        @Column
        private Boolean status;
}
