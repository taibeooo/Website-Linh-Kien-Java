package Midterm.webgk.Controller;

import Midterm.webgk.Model.Brand;
import Midterm.webgk.Model.Category;
import Midterm.webgk.Model.Company;
import Midterm.webgk.Model.Product;
import Midterm.webgk.Service.BrandService;
import Midterm.webgk.Service.CategoryService;
import Midterm.webgk.Service.CompanyService;
import Midterm.webgk.Service.ProductSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AdminController {
    private final ProductSerivce productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;
    private final BrandService brandService;

    @Autowired
    public AdminController(ProductSerivce productService, CategoryService categoryService, CompanyService companyService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.brandService = brandService;
    }

    @GetMapping ("/admin")
    public String loadAdminPage(Model model){
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        return "Admin";
    }

    @GetMapping("/addproduct")
    public String showALLPRODUCT(Model model){
        List<Company> companyList = companyService.getAllCompanies();
        List<Category> categoryList = categoryService.getAllCategories();
        List<Brand> brandList = brandService.getAllBrand();
        model.addAttribute("companyList", companyList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        return "AddProduct";
    }
    @PostMapping("/addproduct")
    public RedirectView addProduct(@RequestParam("name_product")String name_product, @RequestParam("price_product")int price_product, @RequestParam("img_product")String img_product,@RequestParam("soluong") Integer soluong,@RequestParam("color_product")String color_product,@RequestParam("des_product")String des_product	,@RequestParam("categoryID") Integer categoryID,@RequestParam("companyID") Integer companyID,@RequestParam("brandID") Integer brandID){
//        productService.addProduct(name_product,price_product,img_product,categoryID,companyID,soluong,color_product,des_product);
        productService.addProduct(name_product,price_product,img_product,soluong,color_product,des_product,categoryID,companyID,brandID);
        return new RedirectView("/admin");
    }



    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Integer productId) {
        if(productService.deleteProduct(productId)){

            return "redirect:/admin";
        }
        return "Xoá không thành công";
    }
    @GetMapping("/edit/{productId}")
    public String editProduct(@PathVariable("productId") Integer productId, Model model) {
        // Lấy thông tin sản phẩm cần chỉnh sửa từ productId
        Product productToEdit = productService.findByIdInt(productId);

        // Kiểm tra xem sản phẩm tồn tại hay không
        if (productToEdit != null) {
            // Lấy danh sách categories và companies

            // Đưa thông tin sản phẩm cần chỉnh sửa và danh sách categories/companies vào model
            model.addAttribute("productToEdit", productToEdit);

            // Trả về view để chỉnh sửa sản phẩm
            return "editproduct";
        } else {
            // Nếu không tìm thấy sản phẩm, có thể xử lý thông báo lỗi hoặc chuyển hướng đến trang khác
            return "ProductNotFound";
        }
    }
    @PostMapping("/edit/{productId}")
    public RedirectView updateProduct(@PathVariable("productId") Integer productId, @RequestParam("name_product") String name_product, @RequestParam("price_product") int price_product, @RequestParam("img_product") String img_product) {
        // Tạo một đối tượng Product với thông tin mới từ form
        Product updatedProduct = new Product();
        updatedProduct.setNameProduct(name_product);
        updatedProduct.setPriceProduct(price_product);
        updatedProduct.setImgProduct(img_product);

        // Gọi service để cập nhật thông tin sản phẩm
        productService.updateProduct(productId, updatedProduct);
        // Sau khi cập nhật, chuyển hướng về trang admin
        return new RedirectView("/admin");
    }
}
