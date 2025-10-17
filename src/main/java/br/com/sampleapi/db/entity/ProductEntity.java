package br.com.sampleapi.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Builder(toBuilder = true, builderClassName = "Builder")
@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

        @Id
        @GeneratedValue
        @UuidGenerator
        private UUID productId;

        @Column
        private String description;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "categoryId",
                referencedColumnName = "categoryId",
                foreignKey = @ForeignKey(name = "product_category_id_fk")
        )
        private CategoryEntity category;
}
