package Midterm.webgk.Service;

import Midterm.webgk.Model.Brand;
import Midterm.webgk.Model.Category;
import Midterm.webgk.Model.Company;
import Midterm.webgk.Model.Product;
import Midterm.webgk.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSerivce {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CompanyService companyService;
    private final BrandService brandService;

    @Autowired
    public ProductSerivce(ProductRepository productRepository, CategoryService categoryService, CompanyService companyService, BrandService brandService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.brandService = brandService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product findByIdInt(int userid){
        return productRepository.findByIdInt(userid);
    }


    public void addProduct(String nameProduct, int priceProduct, String imgProduct,int soluong,String Color,String desc, Integer categoryId, Integer companyId,Integer brandid) {
        Category category = categoryService.getCategoryById(categoryId);
        Company company = companyService.getCompanyById(companyId);
        Brand brand = brandService.getBrandById(brandid);
        Product newProduct = new Product(nameProduct, priceProduct, imgProduct, category, company,soluong,Color,desc,brand);
        productRepository.save(newProduct);
    }
    public boolean deleteProduct(int productid) {
        try {
            productRepository.deleteProduct(productid);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Log thông tin lỗi
            return false;
        }
    }
    public List<Product> findByCategoryid(Integer categoryId) {
        return productRepository.findByCategoryid(categoryService.getCategoryById(categoryId));
    }
    public List<Product> findByCompanyid(Integer companyID) {
        return productRepository.findByCompanyid(companyService.getCompanyById(companyID));
    }
    public List<Product> findByBrandid(Integer brandid) {
        return productRepository.findByBrandid(brandService.getBrandById(brandid));
    }
    public List<Product> findPrice(Integer price) {
        return productRepository.findPrice(price);
    }

    public List<Product> findByCompanyidAndCategoryid(Integer categoryId,Integer companyID) {
        return productRepository.findByCompanyidAndCategoryid(categoryService.getCategoryById(categoryId),companyService.getCompanyById(companyID));
    }

    public List<Product> findByCompanyidAndfindByBrandid(Integer BrandID,Integer companyID) {
        return productRepository.findByCompanyidAndfindByBrandid(brandService.getBrandById(BrandID),companyService.getCompanyById(companyID));
    }

    public List<Product> findByCategoryidAndfindByBrandid(Integer BrandID,Integer categoryId) {
        return productRepository.findByCategoryidAndfindByBrandid(brandService.getBrandById(BrandID),categoryService.getCategoryById(categoryId));
    }
    public List<Product> findByCompanyidAndPrice(Integer Price,Integer companyid) {
        return productRepository.findByCompanyidAndPrice(Price,companyService.getCompanyById(companyid));
    }
    public List<Product> findByCategoryidAndPrice(Integer Price,Integer categori) {
        return productRepository.findByCategoryidAndPrice(Price,categoryService.getCategoryById(categori));
    }

    public List<Product> findByBrandidAndPrice(Integer Price,Integer brandid) {
        return productRepository.findByBrandidAndPrice(Price,brandService.getBrandById(brandid));
    }
    public List<Product> find3(Integer BrandID,Integer categoryId,Integer companyID) {
        return productRepository.find3(brandService.getBrandById(BrandID),categoryService.getCategoryById(categoryId),companyService.getCompanyById(companyID));
    }
    public List<Product> find4(Integer BrandID,Integer categoryId,Integer companyID,Integer price) {
        return productRepository.find4(brandService.getBrandById(BrandID),categoryService.getCategoryById(categoryId),companyService.getCompanyById(companyID),price);
    }
    public List<Product> findByCompanyBrandPrice(Integer BrandID,Integer company,Integer price) {
        return productRepository.findByCompanyBrandPrice(brandService.getBrandById(BrandID),companyService.getCompanyById(company),price);
    }
    public List<Product> findByCategoryBrandPrice(Integer BrandID,Integer categori,Integer price) {
        return productRepository.findByCategoryBrandPrice(brandService.getBrandById(BrandID),categoryService.getCategoryById(categori),price);
    }

    public List<Product> findByCategoryCompanyPrice(Integer company,Integer categori,Integer price) {
        return productRepository.findByCategoryCompanyPrice(companyService.getCompanyById(company),categoryService.getCategoryById(categori),price);
    }

    public void updateProduct(Integer productId, Product updatedProduct) {
        // Tìm sản phẩm cần cập nhật từ cơ sở dữ liệu
        Optional<Product> productOptional = productRepository.findById(productId);

        // Kiểm tra xem sản phẩm có tồn tại hay không
        if (productOptional.isPresent()) {
            // Lấy sản phẩm đã tồn tại từ Optional
            Product existingProduct = productOptional.get();

            // Cập nhật thông tin sản phẩm từ updatedProduct vào sản phẩm đã tồn tại
            existingProduct.setNameProduct(updatedProduct.getNameProduct());
            existingProduct.setPriceProduct(updatedProduct.getPriceProduct());
            existingProduct.setImgProduct(updatedProduct.getImgProduct());
            // Cập nhật CategoryID và CompanyID nếu cần

            // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu thông qua JpaRepository
            productRepository.save(existingProduct);
        } else {
            // Xử lý khi không tìm thấy sản phẩm để cập nhật (có thể làm báo lỗi hoặc xử lý tùy theo yêu cầu của bạn)
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
    }


    public void addProduct(Product product) {
        productRepository.save(product);
    }
}