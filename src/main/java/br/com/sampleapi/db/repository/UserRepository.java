package br.com.sampleapi.db.repository;

import br.com.sampleapi.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {


    @Query(
            nativeQuery = true,
            value = """
                    SELECT *
                    FROM USERS
                    WHERE (:name IS NULL OR name LIKE :name)
                    LIMIT :size OFFSET :page
                    """
    )
    List<UserEntity> find(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT count(1)
                    FROM USERS
                    WHERE (:name IS NULL OR name LIKE :name)
                    """
    )
    Long count(@Param("name") String name);

}
