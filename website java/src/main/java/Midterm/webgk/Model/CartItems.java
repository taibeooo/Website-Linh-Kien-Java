package Midterm.webgk.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartitems")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartitemID")
    private int cartitemID;
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "productId")
    private int productId;
    @Column(name = "UserID")
    private String UserID;
    public CartItems(String UserID, int productId, int quantity) {
        UserID = UserID;
        productId = productId;
        quantity = quantity;
    }
}
