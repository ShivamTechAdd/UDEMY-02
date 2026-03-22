package com.ecomerce.sbecom.Service;

import com.ecomerce.sbecom.Exceptions.ApiException;
import com.ecomerce.sbecom.Exceptions.ResourceNotFoundException;
import com.ecomerce.sbecom.Model.Category;
import com.ecomerce.sbecom.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getallCategories() {
        List<Category> ls = categoryRepository.findAll();
        if(ls.isEmpty())
            throw new ApiException("No category exist currently!!!");
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        Category savedcategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedcategory!=null)
            throw new ApiException("Category with the name "+category.getCategoryName()+" already exists  !!!!");
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        categoryRepository.delete(category);
        return "Category deleted with id "+category.getCategoryId()+" Successfully";
    }

    @Override
    public Category updateCategory(Category category , Long id) {
        Optional<Category> categories = categoryRepository.findById(id);

        Category savedCategory = categories.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",id));

        category.setCategoryId(id);
        categoryRepository.save(savedCategory);
        return savedCategory;
    }


}
