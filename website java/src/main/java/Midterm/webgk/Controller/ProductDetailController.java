package Midterm.webgk.Controller;

import Midterm.webgk.Model.Product;
import Midterm.webgk.Service.ProductSerivce;
import Midterm.webgk.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductDetailController {
    @Autowired
    ProductSerivce productSerivce ;
    @Autowired
    public ProductDetailController(ProductSerivce productService) {
        this.productSerivce = productService;
    }
    @GetMapping ("/productdetail/{productid}")
    public String productdetail(Model model, @PathVariable("productid") int productid) {
        Product product = productSerivce.findByIdInt(productid);
        model.addAttribute("product", product);
        return "ProductDetail";
    }
}
