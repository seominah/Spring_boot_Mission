package dev.sma.basic.controller;

import dev.sma.basic.model.CategoryDto;
import dev.sma.basic.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    public CategoryController(@Autowired CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto dto){
        return ResponseEntity.ok(this.categoryService.createCategory(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> readCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.categoryService.readCategory(id));
    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> readAllCategory(){
        return ResponseEntity.ok(this.categoryService.readAllCategory());
    }
}
