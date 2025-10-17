package br.com.sampleapi.db.repository;

import br.com.sampleapi.db.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {


    List<PersonEntity> findAllByOrderByNameAsc();
}
