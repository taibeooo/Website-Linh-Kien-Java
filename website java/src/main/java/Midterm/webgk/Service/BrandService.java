package Midterm.webgk.Service;

import Midterm.webgk.Model.Brand;
import Midterm.webgk.Model.Company;
import Midterm.webgk.Repository.BrandRepository;
import Midterm.webgk.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    public List<Brand> getAllBrand() {
        return brandRepository.findALLbrand();
    }
    public Brand getBrandById(Integer BrandId) {
        return brandRepository.findById(BrandId).orElse(null);
    }

}
