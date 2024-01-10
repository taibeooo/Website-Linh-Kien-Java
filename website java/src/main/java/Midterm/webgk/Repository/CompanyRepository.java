package Midterm.webgk.Repository;

import Midterm.webgk.Model.CartItems;
import Midterm.webgk.Model.Category;
import Midterm.webgk.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT p FROM Company p")
    List<Company> findALLCompany();
}
