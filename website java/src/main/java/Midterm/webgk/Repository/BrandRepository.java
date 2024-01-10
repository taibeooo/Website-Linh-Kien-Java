package Midterm.webgk.Repository;

import Midterm.webgk.Model.Brand;
import Midterm.webgk.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("SELECT p FROM Brand p")
    List<Brand> findALLbrand();
    @Query(value = "select v from Brand v where v.nameBrand = :NameBrand")
    Brand findByBrandName(@Param("NameBrand")String NameBrand);
}
