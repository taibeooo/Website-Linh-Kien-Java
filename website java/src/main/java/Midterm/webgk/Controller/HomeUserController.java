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

import java.util.List;
@Controller
public class HomeUserController {
    public final ProductSerivce productService;
    public final CategoryService categoryService;
    public final CompanyService companyService;
    public final BrandService brandService;

    @Autowired
    public HomeUserController(ProductSerivce productService, CategoryService categoryService, CompanyService companyService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.brandService = brandService;
    }

    @GetMapping("/homeuser/{userid}")
    public String home(Model model, @PathVariable("userid") String userID) {
        model.addAttribute("user", userID);
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        List<Company> companies = companyService.getAllCompanies();
        List<Brand>  brand = brandService.getAllBrand();

        model.addAttribute("companies", companies);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("brand", brand);
        return "homeuser";
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "index";
    }
    @GetMapping("/homeuser/{userid}/filterByCategory")
    public String filterByCategory(@PathVariable("userid") String userID,@RequestParam(value = "categoryID", required = false) Integer categoryId,@RequestParam(value = "companyID", required = false) Integer companyId,@RequestParam(value = "brandID", required = false) Integer brandId, @RequestParam(value = "price", required = false) Integer prices,Model model) {
        model.addAttribute("user", userID);
        if (prices != null && companyId != null && brandId != null && categoryId !=null) {
            // Lọc dữ liệu khi có categoryId và companyId
            List<Product> filteredProducts = productService.find4(brandId,categoryId, companyId,prices);
            model.addAttribute("products", filteredProducts);
        } else if (categoryId != null && companyId != null && brandId != null) {
            // Lọc dữ liệu khi có cả 3 điều kiện
            List<Product> filteredProducts = productService.find3(brandId,categoryId, companyId);
            model.addAttribute("products", filteredProducts);
        } else if (prices != null && companyId != null && brandId != null) {
            // Lọc dữ liệu khi có categoryId và companyId
            List<Product> filteredProducts = productService.findByCompanyBrandPrice(brandId, companyId,prices);
            model.addAttribute("products", filteredProducts);
        } else if (prices != null && categoryId != null && brandId != null) {
            // Lọc dữ liệu khi có categoryId và companyId
            List<Product> filteredProducts = productService.findByCategoryBrandPrice(brandId, categoryId,prices);
            model.addAttribute("products", filteredProducts);
        } else if (prices != null && categoryId != null && companyId != null) {
            // Lọc dữ liệu khi có categoryId và companyId
            List<Product> filteredProducts = productService.findByCategoryCompanyPrice(companyId, categoryId,prices);
            model.addAttribute("products", filteredProducts);
        }else if (categoryId != null && companyId != null) {
            // Lọc dữ liệu khi có categoryId và companyId
            List<Product> filteredProducts = productService.findByCompanyidAndCategoryid(categoryId, companyId);
            model.addAttribute("products", filteredProducts);
        } else if (categoryId != null && brandId != null) {
            List<Product> filteredProducts = productService.findByCategoryidAndfindByBrandid(brandId,categoryId);
            model.addAttribute("products", filteredProducts);
        } else if (companyId != null && brandId != null) {
            List<Product> filteredProducts = productService.findByCompanyidAndfindByBrandid(brandId,companyId);
            model.addAttribute("products", filteredProducts);
        }else if (companyId != null && prices != null) {
            List<Product> filteredProducts = productService.findByCompanyidAndPrice(prices,companyId);
            model.addAttribute("products", filteredProducts);
        }else if (categoryId != null && prices != null) {
            List<Product> filteredProducts = productService.findByCategoryidAndPrice(prices,categoryId);
            model.addAttribute("products", filteredProducts);
        }else if (brandId != null && prices != null) {
            List<Product> filteredProducts = productService.findByBrandidAndPrice(prices,brandId);
            model.addAttribute("products", filteredProducts);
        }else if (categoryId != null) {
            // Lọc dữ liệu khi chỉ có categoryId
            List<Product> filteredProducts = productService.findByCategoryid(categoryId);
            model.addAttribute("products", filteredProducts);
        } else if (companyId != null) {
            // Lọc dữ liệu khi chỉ có companyId
            List<Product> filteredProducts = productService.findByCompanyid(companyId);
            model.addAttribute("products", filteredProducts);
        } else if (brandId != null) {
            // Lọc dữ liệu khi chỉ có brandID
            List<Product> filteredProducts = productService.findByBrandid(brandId);
            model.addAttribute("products", filteredProducts);
        } else if (prices != null) {
            List<Product> filteredProducts = productService.findPrice(prices);
            model.addAttribute("products", filteredProducts);
        } else {
            // Xử lý khi không có điều kiện nào được chọn
            List<Product> allProducts = productService.getAllProducts();
            model.addAttribute("products", allProducts);
            model.addAttribute("message", "Vui lòng chọn ít nhất một điều kiện.");
        }
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        List<Brand>  brand = brandService.getAllBrand();
        model.addAttribute("brand", brand);
        return "homeuser";
    }
}
