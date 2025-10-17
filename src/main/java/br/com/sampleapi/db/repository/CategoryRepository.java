package br.com.sampleapi.db.repository;

import br.com.sampleapi.db.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {


    Optional<CategoryEntity> findByNameAndStatus(String name, Boolean status);

    @Query(nativeQuery = true, value = "SELECT * FROM category " +
            "WHERE (:name IS NULL OR name LIKE '%' || :name || '%' ) ")
    Optional<CategoryEntity> custom(String name);
}
