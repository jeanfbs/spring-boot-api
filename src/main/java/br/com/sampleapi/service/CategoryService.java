package br.com.sampleapi.service;

import br.com.sampleapi.db.repository.CategoryRepository;
import br.com.sampleapi.dto.Category;
import br.com.sampleapi.exception.DataNotFoundException;
import br.com.sampleapi.service.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    private CategoryMapper mapper;
    private CategoryRepository repository;

    public Category find() {
        var result = repository.custom("teste")
                .orElseThrow(() -> new DataNotFoundException("Category id not found"));
        return mapper.toDto(result);
    }

    public Category findByNameAndStatus(String name, Boolean status) {
        var entity = repository.findByNameAndStatus(name, status)
                .orElseThrow(() -> new DataNotFoundException("Category not found: name: %s, status: %s"
                                .formatted(name, status)
                        )
                );
        return mapper.toDto(entity);
    }
}
