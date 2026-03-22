package com.ecomerce.sbecom.Repository;

import com.ecomerce.sbecom.Model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);  //Automatic implimentaion by jpa
}
