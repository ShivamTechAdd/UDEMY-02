package com.ecomerce.sbecom.Service;

import com.ecomerce.sbecom.Model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getallCategories();
    void createCategory(Category category);
    String deleteCategory(Long id);

    Category updateCategory(Category category,Long id);

}
