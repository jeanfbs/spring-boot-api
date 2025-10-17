package br.com.sampleapi.resource.api;

import br.com.sampleapi.dto.PaginationResponse;
import br.com.sampleapi.dto.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Tag(name = "Person", description = "The persons API")
public interface PersonApi {

    @Operation(summary = "Create a new person", tags = {"Person"})
    @PostMapping("/persons")
    default ResponseEntity<?> create(@Valid @RequestBody Person request){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "List all persons", tags = {"Person"})
    @GetMapping("/persons")
    default ResponseEntity<List<Person>> listAll() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get all persons by filters", tags = {"Person"})
    @GetMapping("/persons/{id}/age")
    default ResponseEntity<?> find(@PathVariable("id") Long id, @RequestParam String output) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get all persons by filters", tags = {"Person"})
    @GetMapping("/persons/{id}/salary")
    default ResponseEntity<?> getSalary(@PathVariable("id") Long id, @RequestParam String output) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Find a person by ID", tags = {"Person"})
    @GetMapping("/persons/{id}")
    default ResponseEntity<?> findById(
            @Parameter(name = "id", description = "Person ID", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Update an existing person", tags = {"Person"})
    @RequestMapping(value = "/persons/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT })
    default ResponseEntity<?> update(
            @Parameter(name = "id", description = "Person ID", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Long id,
            @RequestBody Person person) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete a person by ID", tags = {"Person"})
    @DeleteMapping("/persons/{id}")
    default ResponseEntity<?> delete(
            @Parameter(name = "id", description = "Person ID", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
