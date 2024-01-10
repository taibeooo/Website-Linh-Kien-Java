package Midterm.webgk.Repository;

import Midterm.webgk.Model.CartItems;
import Midterm.webgk.Model.Category;
import Midterm.webgk.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT p FROM Category p")
    List<Category> findALLcategory();
    @Query(value = "select v from Category v where v.NameCategory = :NameCategory")
    Category findByCategoryName(@Param("NameCategory")String NameCategory);


}
