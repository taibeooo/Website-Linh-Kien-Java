package Midterm.webgk.Service;

import Midterm.webgk.Model.Category;
import Midterm.webgk.Model.Company;
import Midterm.webgk.Repository.CategoryRepository;
import Midterm.webgk.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findALLCompany();
    }
    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
}
