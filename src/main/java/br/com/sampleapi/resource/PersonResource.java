package br.com.sampleapi.resource;

import br.com.sampleapi.dto.Person;
import br.com.sampleapi.resource.api.BaseResource;
import br.com.sampleapi.resource.api.PersonApi;
import br.com.sampleapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class PersonResource implements BaseResource, PersonApi {

    private PersonService service;

    @Override
    public ResponseEntity<?> create(final Person request) {
        var response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> find(Long id, String output) {
        var response = service.find(id, output);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getSalary(Long id, String output) {
        var response = service.getSalary(id, output);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<Person>> listAll() {
        var response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> update(Long id, Person request) {
        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
