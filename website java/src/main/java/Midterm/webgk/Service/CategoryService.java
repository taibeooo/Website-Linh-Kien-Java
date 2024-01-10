package Midterm.webgk.Service;

import Midterm.webgk.Model.Category;
import Midterm.webgk.Model.Product;
import Midterm.webgk.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findALLcategory();
    }
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category  findByCategoryName(String namecategori){
        return categoryRepository.findByCategoryName(namecategori);
    }


}
