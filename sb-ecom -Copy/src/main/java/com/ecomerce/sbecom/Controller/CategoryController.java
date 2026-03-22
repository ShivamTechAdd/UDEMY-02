package com.ecomerce.sbecom.Controller;

import com.ecomerce.sbecom.Model.Category;
import com.ecomerce.sbecom.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>>getAllCategories(){
        List<Category> ls = categoryService.getallCategories();
        return new ResponseEntity<>(ls,HttpStatus.ACCEPTED);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return ResponseEntity.ok("Category Created successfully");
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
            String status = categoryService.deleteCategory(id);
            return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category , @PathVariable Long id){
        Category savedCategory = categoryService.updateCategory(category , id);
        return new ResponseEntity<>("Category with category id: "+id,HttpStatus.FOUND);
    }

}
