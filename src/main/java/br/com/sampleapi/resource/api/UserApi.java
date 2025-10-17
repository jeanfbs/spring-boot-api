package br.com.sampleapi.resource.api;

import br.com.sampleapi.dto.PaginationResponse;
import br.com.sampleapi.dto.User;
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

import java.util.UUID;

@Validated
@Tag(name = "User", description = "The users API")
public interface UserApi {

    @Operation(summary = "Create a new anything", tags = {"User"})
    @PostMapping("/users")
    default ResponseEntity<?> create(@Valid @RequestBody User request){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get all users by filters", tags = {"User"})
    @GetMapping("/users")
    default ResponseEntity<PaginationResponse<User>> find(@ModelAttribute User request,
                                                          @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                          @Min(1) @Max(100) @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Find a user by ID", tags = {"User"})
    @GetMapping("/users/{id}")
    default ResponseEntity<?> findById(
            @Parameter(name = "id", description = "User ID", required = true, in = ParameterIn.PATH)
            @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Update an existing user", tags = {"User"})
    @PutMapping("/users/{id}")
    default ResponseEntity<?> update(
            @Parameter(name = "id", description = "User ID", required = true, in = ParameterIn.PATH)
            @PathVariable("id") UUID id,
            @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete a user by ID", tags = {"User"})
    @DeleteMapping("/users/{id}")
    default ResponseEntity<?> delete(
            @Parameter(name = "id", description = "User ID", required = true, in = ParameterIn.PATH)
            @PathVariable("id") UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
