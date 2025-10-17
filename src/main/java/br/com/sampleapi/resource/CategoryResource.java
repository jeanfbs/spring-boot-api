package br.com.sampleapi.resource;

import br.com.sampleapi.dto.Category;
import br.com.sampleapi.resource.api.BaseResource;
import br.com.sampleapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryResource implements BaseResource {

    private CategoryService service;

    @GetMapping(value = "/categories")
    @ResponseBody
    public Category find() {
        return service.find();
    }

    @GetMapping(value = "/categories/find")
    public Category findBYNameAndStatus(@RequestParam String nome, @RequestParam Boolean status) {
        return service.findByNameAndStatus(nome, status);
    }

}
