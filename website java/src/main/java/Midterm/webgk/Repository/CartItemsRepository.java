package Midterm.webgk.Repository;

import Midterm.webgk.Model.CartItems;
import Midterm.webgk.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cartitems(userid, product_id, quantity) values (:iduser, :idproduct, :quantity)", nativeQuery = true)
    void insertCartitems(@Param("iduser") String iduser, @Param("idproduct") Integer idproduct, @Param("quantity") Integer quantity);


    @Query(value ="select u from CartItems u where u.UserID = :userid ")
    List<CartItems> finduserid(@Param("userid")String userid);

    @Transactional
    @Modifying
    @Query("update CartItems v set v.quantity = :quantity where v.UserID = :iduser and v.productId = :idproduct")
    void updateCartitems(@Param("iduser") String iduser, @Param("idproduct") Integer idproduct, @Param("quantity") int quantity);

    @Query("select c from CartItems c where c.productId = :idproduct and c.UserID = :iduser")
    CartItems checkProductExists(@Param("idproduct") Integer idproduct, @Param("iduser") String iduser);

    @Transactional
    @Modifying
    @Query("delete from CartItems c where c.productId = :idproduct and c.UserID = :iduser")
    void deleteByProductIdAndUserID(@Param("idproduct") Integer idproduct, @Param("iduser") String iduser);

    @Transactional
    @Modifying
    @Query("delete from CartItems c where c.UserID = :iduser")
    void deleteByProductUserID(@Param("iduser") String iduser);
}
