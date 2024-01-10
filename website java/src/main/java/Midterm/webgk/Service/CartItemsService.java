package Midterm.webgk.Service;


import Midterm.webgk.Model.CartItems;
import Midterm.webgk.Repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemsService {
    @Autowired
    CartItemsRepository cartItemsRepository;
    public boolean insertCartitems(String iduser, int idproduct, int quantity){
        try{
            cartItemsRepository.insertCartitems(iduser, idproduct, quantity);
            return true;
        }catch (Exception e){}
        return false;
    }

    public List<CartItems> findbyid(String userid){
        try{
            List<CartItems> cartItems =cartItemsRepository.finduserid(userid);
            return cartItems;
        }catch (Exception e){}
        return null;
    }
    public boolean updateCartitems(String iduser, int idproduct, int quantity){
        try {
            CartItems cartItems = cartItemsRepository.checkProductExists(idproduct, iduser);

            if(cartItems != null){
                cartItemsRepository.updateCartitems(iduser, idproduct, cartItems.getQuantity()+quantity);
            }
            else{
                cartItemsRepository.insertCartitems(iduser, idproduct, quantity);
            }
            return true;
        } catch (Exception e) {
            // Handle exception appropriately
        }

        return false;
    }


    public boolean deleteByProductIdAndUserID(int idproduct, String iduser) {
        try {
            CartItems cartItems = cartItemsRepository.checkProductExists(idproduct, iduser);
            if (cartItems != null) {
                cartItemsRepository.deleteByProductIdAndUserID(idproduct, iduser);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log thông tin lỗi
            return false;
        }
    }

    public boolean deleteByProductUserID(String iduser) {
        try {
                cartItemsRepository.deleteByProductUserID(iduser);
                return true;
        } catch (Exception e) {
            e.printStackTrace(); // Log thông tin lỗi
            return false;
        }
    }
}
