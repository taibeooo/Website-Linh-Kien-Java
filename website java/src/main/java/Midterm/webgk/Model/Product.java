package Midterm.webgk.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String NameProduct;
    private String DesProduct;
    private int PriceProduct;
    private String ImgProduct;
    private String ColorProduct;
    private Integer soluong;
    @ManyToOne
    @JoinColumn(name = "Categoryid",referencedColumnName = "categoryID")
    private Category Categoryid;
    @ManyToOne
    @JoinColumn(name = "Companyid",referencedColumnName = "companyID")
    private Company Companyid;
    @ManyToOne
    @JoinColumn(name = "BrandID",referencedColumnName = "brandID")
    private Brand BrandID;




    public Product(String nameProduct, int priceProduct, String imgProduct, Category categoryid, Company companyid,int sl,String color,String desc,Brand brand) {
        NameProduct = nameProduct;
        PriceProduct = priceProduct;
        ImgProduct = imgProduct;
        Categoryid = categoryid;
        Companyid = companyid;
        soluong = sl;
        ColorProduct = color;
        DesProduct = desc;
        BrandID = brand;
    }
}
