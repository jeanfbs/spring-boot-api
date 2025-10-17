package br.com.sampleapi.service;

import br.com.sampleapi.db.entity.PersonEntity;
import br.com.sampleapi.db.repository.PersonRepository;
import br.com.sampleapi.dto.Person;
import br.com.sampleapi.enums.AgeType;
import br.com.sampleapi.enums.SalaryType;
import br.com.sampleapi.exception.DataNotFoundException;
import br.com.sampleapi.exception.RegisterAlreadyExistException;
import br.com.sampleapi.service.mapper.ClassMerger;
import br.com.sampleapi.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonMapper mapper;
    private final PersonRepository repository;

    public Person create(final Person person) {
        if (person.id() != null) {
            repository.findById(person.id()).ifPresent(personEntity -> {
                throw new RegisterAlreadyExistException("Person already exist");
            });
        }
        final var entity = repository.save(mapper.toEntity(person));
        return mapper.toDto(entity);
    }

    public Map<String, Long> find(Long id, String output) {
        AgeType outputType = AgeType.fromName(output);

        PersonEntity entity = findEntityById(id);
        Long result = outputType.getConverter().apply(entity.getAdmissionAt());
        return Map.of(outputType.getName(), result);
    }


    public Map<String, String> getSalary(Long id, String output) {
        SalaryType salaryType = SalaryType.fromName(output);
        AgeType outputType = AgeType.YEARS;
        PersonEntity entity = findEntityById(id);
        Long years = outputType.getConverter().apply(entity.getAdmissionAt());
        String salary = salaryType.getConverter().apply(years);
        return Map.of("salary", salary);
    }

    public List<Person> findAll() {
        return repository.findAllByOrderByNameAsc().stream().map(mapper::toDto).toList();
    }

    public Person findById(Long id) {
        var entity = findEntityById(id);
        return mapper.toDto(entity);
    }

    private PersonEntity findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Person id %s not found".formatted(id)));
    }

    public Person update(final Long id, final Person update) {
        PersonEntity entity = mapper.toEntity(update);
        PersonEntity currentEntity = findEntityById(id);
        return mapper.toDto(repository.save(ClassMerger.merge(entity, currentEntity)));
    }

    public void delete(Long id) {
        PersonEntity entity = findEntityById(id);
        repository.delete(entity);
    }


}
