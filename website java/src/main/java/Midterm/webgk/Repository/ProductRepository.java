package Midterm.webgk.Repository;

import Midterm.webgk.Model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    @Query("select v from Product v where v.productId = :productId")
    Product findByIdInt(@Param("productId")int productId);
    @Query("select v from Product v where v.Categoryid = :Categoryid")
    List<Product> findByCategoryid(@Param("Categoryid")Category Categoryid);

    @Query("select v from Product v where v.Companyid = :Companyid")
    List<Product> findByCompanyid(@Param("Companyid") Company Companyid);

    @Query("select v from Product v where v.BrandID = :Brandid")
    List<Product> findByBrandid(@Param("Brandid") Brand Brandid);

    @Query("select v from Product v where v.PriceProduct < :Price")
    List<Product> findPrice(@Param("Price")int Price);

    @Query("select v from Product v where v.Companyid = :Companyid and v.Categoryid = :Categoryid")
    List<Product> findByCompanyidAndCategoryid(@Param("Categoryid")Category Categoryid,@Param("Companyid") Company Companyid);


    @Query("select v from Product v where v.Companyid = :Companyid and v.PriceProduct < :Price")
    List<Product> findByCompanyidAndPrice(@Param("Price")int Price,@Param("Companyid") Company Companyid);


    @Query("select v from Product v where v.Categoryid = :Categoryid and v.PriceProduct < :Price")
    List<Product> findByCategoryidAndPrice(@Param("Price")int Price,@Param("Categoryid") Category Categoryid);

    @Query("select v from Product v where v.BrandID = :BrandID and v.PriceProduct < :Price")
    List<Product> findByBrandidAndPrice(@Param("Price")int Price,@Param("BrandID") Brand BrandID);

    @Query("select v from Product v where v.Companyid = :Companyid and v.BrandID = :BrandID")
    List<Product> findByCompanyidAndfindByBrandid(@Param("BrandID")Brand BrandID,@Param("Companyid") Company Companyid);

    @Query("select v from Product v where v.Companyid = :Companyid and v.BrandID = :BrandID and v.PriceProduct < :Price")
    List<Product> findByCompanyBrandPrice(@Param("BrandID")Brand BrandID,@Param("Companyid") Company Companyid,@Param("Price")int Price);

    @Query("select v from Product v where v.Categoryid = :Categoryid and v.BrandID = :BrandID and v.PriceProduct < :Price")
    List<Product> findByCategoryBrandPrice(@Param("BrandID")Brand BrandID,@Param("Categoryid") Category Categoryid,@Param("Price")int Price);

    @Query("select v from Product v where v.Categoryid = :Categoryid and v.Companyid = :Companyid and v.PriceProduct < :Price")
    List<Product> findByCategoryCompanyPrice(@Param("Companyid")Company Companyid,@Param("Categoryid") Category Categoryid,@Param("Price")int Price);


    @Query("select v from Product v where v.Categoryid = :Categoryid and v.BrandID = :BrandID")
    List<Product> findByCategoryidAndfindByBrandid(@Param("BrandID")Brand BrandID,@Param("Categoryid") Category Categoryid);


    @Query("select v from Product v where v.Categoryid = :Categoryid and v.BrandID = :BrandID and v.Companyid = :Companyid")
    List<Product> find3(@Param("BrandID")Brand BrandID,@Param("Categoryid") Category Categoryid,@Param("Companyid") Company Companyid);

    @Query("select v from Product v where v.Categoryid = :Categoryid and v.BrandID = :BrandID and v.Companyid = :Companyid and v.PriceProduct < :Price")
    List<Product> find4(@Param("BrandID")Brand BrandID,@Param("Categoryid") Category Categoryid,@Param("Companyid") Company Companyid,@Param("Price")int Price);


    @Transactional
    @Modifying
    @Query("delete from Product c where c.productId = :idproduct")
    void deleteProduct(@Param("idproduct") Integer idproduct);

}