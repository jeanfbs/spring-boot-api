package br.com.sampleapi.service;

import br.com.sampleapi.db.entity.UserEntity;
import br.com.sampleapi.db.repository.UserRepository;
import br.com.sampleapi.dto.PaginationResponse;
import br.com.sampleapi.dto.User;
import br.com.sampleapi.exception.DataNotFoundException;
import br.com.sampleapi.service.mapper.ClassMerger;
import br.com.sampleapi.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public User create(final User user) {
        final var entity = repository.save(mapper.toEntity(user));
        return mapper.toDto(entity);
    }

    public PaginationResponse<User> find(User request, Integer page, Integer size) {
        var calculatedPage = ((page - 1) * size);
        List<UserEntity> results = repository.find(request.name(), calculatedPage, size);
        Long totalFiltered = repository.count(request.name());
        final var totalPages = totalFiltered / size;
        return PaginationResponse.<User>builder()
                .content(results.stream().map(mapper::toDto).toList())
                .totalFiltered(totalFiltered)
                .totalPages(totalPages > 0 ? totalPages : totalFiltered != 0 ? 1 : 0)
                .first(results.size() < size || page == 1)
                .last(page == (totalPages > 0 ? totalPages : 1))
                .build();
    }

    public User findById(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    private UserEntity findEntityById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User id %s not found".formatted(id)));
    }

    public User update(final UUID id, final User update) {
        UserEntity entity = mapper.toEntity(update);
        UserEntity currentEntity = findEntityById(id);
        return mapper.toDto(repository.save(ClassMerger.merge(entity, currentEntity)));
    }

    public void delete(UUID id) {
        UserEntity entity = findEntityById(id);
        repository.delete(entity);
    }
}
